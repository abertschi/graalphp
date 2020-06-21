package org.graalphp.parser;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.EmptyExprNode;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.function.PhpFunctionRootNode;
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

    private static final Logger LOG = PhpLogger.getLogger(StmtVisitor.class.getSimpleName());

    private final PhpLanguage language;
    private List<PhpStmtNode> stmts;

    private ExprVisitor exprVisitor;
    private ParseScope scope;

    public StmtVisitor(PhpLanguage lang) {
        this.exprVisitor = new ExprVisitor(lang);
        this.language = lang;
    }

    public StmtVisitorContext createPhpAst(Program p) {
        this.stmts = new LinkedList<>();
        this.scope = new ParseScope(new FrameDescriptor());
        this.scope.setGlobal(this.scope);

        for (Statement s : p.statements()) {
            s.accept(this);
        }
        return new StmtVisitorContext(stmts, this.scope);
    }

    public StmtVisitorContext createPhpStmtAst(Statement stmt, ParseScope scope) {
        this.scope = scope;
        this.stmts = new LinkedList<>();
        stmt.accept(this);
        return new StmtVisitorContext(stmts, this.scope);
    }

    private void setSourceSection(PhpStmtNode target, ASTNode n) {
        target.setSourceSection(n.getStart(), n.getLength());
    }

    // ---------------- expression statements --------------------

    @Override
    public boolean visit(ExpressionStatement expressionStatement) {
        PhpExprNode phpExpr = exprVisitor.createExprAst(expressionStatement.getExpression(), scope);
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
        final PhpReturnNode returnNode;
        if (ret.getExpression() == null) {
            returnNode = new PhpReturnNode(new EmptyExprNode());
        } else {
            final PhpExprNode ex = this.exprVisitor
                    .createExprAst(ret.getExpression(), getCurrentScope());
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
        List<PhpStmtNode> bodyStmts = parseParameters(fnParse.formalParameters());

        if (fnParse.getBody() != null) {
            final StmtVisitor fnVisitor = new StmtVisitor(this.language);
            final StmtVisitorContext body = fnVisitor.createPhpStmtAst(fnParse.getBody(), currFunctionScope);
            bodyStmts.addAll(body.stmts);
        }
        fnRoot = PhpFunctionRootNode.createFromStmts(language, currFunctionScope.getFrameDesc(),
                fnName,
                bodyStmts);
        final PhpFunction function = new PhpFunction(fnName, scope,
                Truffle.getRuntime().createCallTarget(fnRoot));

        scope.getFunctions().registerOrUpdate(fnName, function, true);
        LOG.info("create call target for " + fnName);

        this.currFunctionScope = null;
        return false;
    }

    private List<PhpStmtNode> parseParameters(List<FormalParameter> parms) {
        final List<PhpStmtNode> bodyStmts = new LinkedList<>();
        for (FormalParameter node : parms) {
            this.currFunctionParamExpr = null;
            node.accept(this);

            assert (currFunctionParamExpr != null);
            bodyStmts.add(currFunctionParamExpr);
            LOG.info("adding expr: " + currFunctionParamExpr.toString());
        }
        return bodyStmts;
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
        setSourceSection(assignNode, formalParameter);

        this.currFunctionArgumentCount++;
        currFunctionParamExpr = assignNode;
        return false;
    }

    private PhpExprNode createLocalAssignment(ParseScope scope,
                                              String target,
                                              PhpExprNode source,
                                              Integer argumentId) {
        final FrameSlot frameSlot = scope.getFrameDesc()
                .findOrAddFrameSlot(target, argumentId, FrameSlotKind.Illegal);

        scope.getVars().put(target, frameSlot);

        // TODO: source section?
        final PhpExprNode assign = PhpWriteVarNodeGen.create(source, frameSlot);
        return assign;
    }

    /**
     * Represents return object of ast walking
     **/
    public static class StmtVisitorContext {

        private final ParseScope scope;
        private final List<PhpStmtNode> stmts;

        public StmtVisitorContext(List<PhpStmtNode> stmts,
                                  ParseScope scope) {
            this.stmts = stmts;
            this.scope = scope;
        }

        public ParseScope getScope() {
            return scope;
        }

        public List<PhpStmtNode> getStmts() {
            return stmts;
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

