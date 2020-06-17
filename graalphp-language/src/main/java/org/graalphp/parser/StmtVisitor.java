package org.graalphp.parser;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpEmptyExprNode;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.PhpFunctionRootNode;
import org.graalphp.nodes.controlflow.PhpReturnNode;
import org.graalphp.nodes.localvar.PhpReadArgNode;
import org.graalphp.nodes.localvar.PhpWriteVarNodeGen;
import org.graalphp.types.PhpFunction;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class StmtVisitor extends HierarchicalVisitor {

    private static final Logger LOG = PhpLogger
            .getLogger(StmtVisitor.class.getSimpleName());

    private final PhpLanguage language;
    private List<PhpStmtNode> stmts;

    private ExprVisitor exprVisitor;
    private ParseScope scope;

    public StmtVisitor(PhpLanguage lang) {
        this.exprVisitor = new ExprVisitor(lang);
        this.language = lang;

    }

    public PhpStmtVisitorContext createPhpAst(Program p) {
        this.stmts = new LinkedList<>();
        this.scope = new ParseScope(new FrameDescriptor());
        this.scope.setGlobal(this.scope);

        for (Statement s : p.statements()) {
            s.accept(this);
        }

        PhpStmtVisitorContext ctx = new PhpStmtVisitorContext(stmts, this.scope);
        // TODO: is this useful? we may rather create a new object for each visiting
        this.stmts = null;
        this.scope = null;
        return ctx;
    }

    public PhpStmtVisitorContext createPhpStmtAst(Statement stmt, ParseScope scope) {
        this.scope = scope;
        this.stmts = new LinkedList<>();
        stmt.accept(this);

        PhpStmtVisitorContext ctx = new PhpStmtVisitorContext(stmts, this.scope);
        this.stmts = null;
        this.scope = null;
        return ctx;
    }


    // ---------------- expression statements --------------------

    @Override
    public boolean visit(ExpressionStatement expressionStatement) {
        PhpExprNode phpExpr =
                exprVisitor.createExprAst(expressionStatement.getExpression(), scope);

        if (phpExpr == null) {
            LOG.info("exprVisitor.createExprAst returned null. " +
                    "This is fine as long as not all features impl. " + expressionStatement.toString());
        } else {
            stmts.add(phpExpr);
        }
        return false;
    }

    // ---------------- return --------------------


    @Override
    public boolean visit(ReturnStatement ret) {
        PhpReturnNode returnNode;
        if (ret.getExpression() == null) {
            returnNode = new PhpReturnNode(new PhpEmptyExprNode());

        } else {
            final PhpExprNode ex = this.exprVisitor.createExprAst(ret.getExpression(), getCurrentScope());
            System.out.println(ex);
            returnNode = new PhpReturnNode(ex);
        }
        stmts.add(returnNode);
        return false;
    }

    // ---------------- function definition --------------------

    private int currFunctionArgumentCount = 0;
    private PhpExprNode currFunctionParamExpr;
    private ParseScope currFunctionScope;

    private ParseScope getCurrentScope() {
        if (currFunctionScope != null) {
            return currFunctionScope;
        }
        return scope;
    }

    @Override
    public void endVisit(FunctionDeclaration fnParse) {
        this.currFunctionScope = null;
        this.currFunctionArgumentCount = 0;
        this.currFunctionParamExpr = null;
    }

    @Override
    public boolean visit(FunctionDeclaration fnParse) {
        // TODO: ignore nullable
        // TODO: ignore return Type

        final String fnName = fnParse.getFunctionName().getName();
        final PhpFunctionRootNode fnRoot;
        this.currFunctionArgumentCount = 0;
        this.currFunctionScope = new ParseScope(new FrameDescriptor(), this.scope.getGlobal());

        final List<PhpStmtNode> bodyStmts = new LinkedList<>();
        for (FormalParameter node : fnParse.formalParameters()) {
            this.currFunctionParamExpr = null;
            node.accept(this);

            assert (currFunctionParamExpr != null);
            bodyStmts.add(currFunctionParamExpr);
            LOG.info("adding expr: " + currFunctionParamExpr.toString());
        }
        if (fnParse.getBody() != null) {
            final StmtVisitor fnVisitor = new StmtVisitor(this.language);
            final PhpStmtVisitorContext body = fnVisitor.createPhpStmtAst(fnParse.getBody(), currFunctionScope);
            bodyStmts.addAll(body.stmts);
        }
        fnRoot = PhpFunctionRootNode.createFromStmts(
                language,
                currFunctionScope.getFrameDesc(),
                fnName,
                bodyStmts);


        final PhpFunction function =
                new PhpFunction(fnName, scope, Truffle.getRuntime().createCallTarget(fnRoot));

        scope.getFunctions().registerOrUpdate(fnName, function, true);
        LOG.info("create call target for " + fnName);

        this.currFunctionScope = null;
        return false;
    }


    @Override
    public boolean visit(FormalParameter formalParameter) {
        assert currFunctionParamExpr == null;

        final PhpReadArgNode readArg = new PhpReadArgNode(this.currFunctionArgumentCount);
        final String name = new IdentifierVisitor()
                .getIdentifierName(formalParameter.getParameterName()).getName();

        final PhpExprNode assignNode = createLocalAssignment(
                this.currFunctionScope,
                name,
                readArg,
                this.currFunctionArgumentCount);

        this.currFunctionArgumentCount++;

        // TODO: source section
        currFunctionParamExpr = assignNode;
        return false;
    }

    private PhpExprNode createLocalAssignment(ParseScope scope,
                                              String target,
                                              PhpExprNode source,
                                              Integer argumentId) {

        final FrameSlot frameSlot = scope.getFrameDesc().findOrAddFrameSlot(
                target,
                argumentId,
                FrameSlotKind.Illegal);

        scope.getVars().put(target, frameSlot);

        // TODO: source section?
        return PhpWriteVarNodeGen.create(source, frameSlot);
    }

    /**
     * Represents return object of ast walking
     **/
    public static class PhpStmtVisitorContext {

        public final ParseScope scope;
        public final List<PhpStmtNode> stmts;

        public PhpStmtVisitorContext(List<PhpStmtNode> stmts,
                                     ParseScope scope) {
            this.stmts = stmts;
            this.scope = scope;
        }

        @Override
        public String toString() {
            return "PhpStmtVisitorContext{" +
                    "scope=" + scope +
                    ", stmts=" + stmts +
                    '}';
        }
    }
}

