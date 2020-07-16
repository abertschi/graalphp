package org.graalphp.parser;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.BreakStatement;
import org.eclipse.php.core.ast.nodes.ContinueStatement;
import org.eclipse.php.core.ast.nodes.DoStatement;
import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.ExpressionStatement;
import org.eclipse.php.core.ast.nodes.ForStatement;
import org.eclipse.php.core.ast.nodes.FormalParameter;
import org.eclipse.php.core.ast.nodes.FunctionDeclaration;
import org.eclipse.php.core.ast.nodes.IfStatement;
import org.eclipse.php.core.ast.nodes.Program;
import org.eclipse.php.core.ast.nodes.Reference;
import org.eclipse.php.core.ast.nodes.ReturnStatement;
import org.eclipse.php.core.ast.nodes.Statement;
import org.eclipse.php.core.ast.nodes.WhileStatement;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.EmptyExprNode;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.StmtListNode;
import org.graalphp.nodes.controlflow.PhpBreakNode;
import org.graalphp.nodes.controlflow.PhpContinueNode;
import org.graalphp.nodes.controlflow.PhpDoWhileNode;
import org.graalphp.nodes.controlflow.PhpForWhileNode;
import org.graalphp.nodes.controlflow.PhpIfNode;
import org.graalphp.nodes.controlflow.PhpReturnNode;
import org.graalphp.nodes.controlflow.PhpWhileNode;
import org.graalphp.nodes.function.PhpFunctionRootNode;
import org.graalphp.nodes.localvar.ReadArgNode;
import org.graalphp.runtime.assign.AssignRuntimeFactory;
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

    public StmtVisitorContext createPhpAst(Program p, ParseScope rootScope) {
        this.stmts = new LinkedList<>();
        this.scope = rootScope;
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
            StringBuilder msg = new StringBuilder()
                    .append("ExprVisitor returned null. This is due to lack of feature support")
                    .append("expression: ")
                    .append(expressionStatement.toString());
            throw new UnsupportedOperationException(msg.toString());
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
            // XXX: By reference or by value semantics are defined by invoke node not by return node
            // this is due to semantics in invoke node always overwrite function definition,
            // i.e. we need to define by-ref in function definition
            // *and* apply it in invoke stmt in order to pass by reference
            returnNode = new PhpReturnNode(ex);
        }
        stmts.add(returnNode);
        return false;
    }

    // ---------------- function definition --------------------

    private int currFunctionArgumentCount = 0;
    private PhpExprNode currFunctionParamExpr;

    // set if we are in a function, use getCurrentScope()
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
        // TODO: langauge feature: parameter hints

        final String fnName = fnParse.getFunctionName().getName();
        this.currFunctionArgumentCount = 0;
        this.currFunctionScope = new ParseScope(new FrameDescriptor(), this.scope.getGlobal());
        List<PhpStmtNode> bodyStmts = parseParameters(fnParse.formalParameters());

        if (fnParse.getBody() != null) {
            final StmtVisitor fnVisitor = new StmtVisitor(this.language);
            final StmtVisitorContext body = fnVisitor.createPhpStmtAst(fnParse.getBody(),
                    currFunctionScope);
            bodyStmts.addAll(body.stmts);
        }

        final PhpFunctionRootNode fnRoot = PhpFunctionRootNode
                .createFromStmts(language, currFunctionScope.getFrameDesc(), fnName, bodyStmts);

        final PhpFunction function = new PhpFunction(fnName, scope,
                Truffle.getRuntime().createCallTarget(fnRoot));
        function.setReturnReference(fnParse.isReference());

        scope.getFunctions().register(fnName, function, false);
        LOG.fine("create call target for " + fnName);

        this.currFunctionScope = null;
        return false;
    }

    private List<PhpStmtNode> parseParameters(List<FormalParameter> parms) {
        final List<PhpStmtNode> bodyStmts = new LinkedList<>();
        for (FormalParameter node : parms) {
            this.currFunctionParamExpr = null;
            node.accept(this);
            assert (currFunctionParamExpr != null) : "function parameter must be set after accept";
            bodyStmts.add(currFunctionParamExpr);
        }
        return bodyStmts;
    }

    private boolean isParameterReference(FormalParameter p) {
        return p.getParameterName() instanceof Reference;
    }

    @Override
    public boolean visit(FormalParameter formalParameter) {
        assert currFunctionParamExpr == null;

        // XXX: We currently support copy by reference semantics for Arrays
        boolean isRef = isParameterReference(formalParameter);
        final PhpExprNode readArgNode = AssignRuntimeFactory
                .createForwardValueNode(isRef, new ReadArgNode(this.currFunctionArgumentCount));

        final String name = formalParameter.getParameterNameIdentifier().getName();

        final PhpExprNode assignNode = VisitorHelpers.createLocalAssignment(
                this.currFunctionScope,
                name,
                readArgNode,
                this.currFunctionArgumentCount,
                formalParameter);
        setSourceSection(assignNode, formalParameter);

        this.currFunctionArgumentCount++;
        currFunctionParamExpr = assignNode;
        return false;
    }

    /**
     * If Statements
     */
    @Override
    public boolean visit(IfStatement ifStmt) {
        final PhpExprNode condition = exprVisitor.createExprAst(ifStmt.getCondition(),
                getCurrentScope());

        final StmtVisitorContext ifContext = new StmtVisitor(this.language)
                .createPhpStmtAst(ifStmt.getTrueStatement(), getCurrentScope());

        final StmtListNode ifBlockNode = new StmtListNode(ifContext.stmts);
        setSourceSection(ifBlockNode, ifStmt.getTrueStatement());

        StmtListNode elseBlockNode = null;
        if (ifStmt.getFalseStatement() != null) {
            final StmtVisitorContext elseContext = new StmtVisitor(this.language)
                    .createPhpStmtAst(ifStmt.getFalseStatement(), getCurrentScope());

            elseBlockNode = new StmtListNode(elseContext.stmts);
            setSourceSection(elseBlockNode, ifStmt.getFalseStatement());
        }

        final PhpIfNode ifElseNode = new PhpIfNode(condition, ifBlockNode, elseBlockNode);
        setSourceSection(ifElseNode, ifStmt);
        stmts.add(ifElseNode);
        return false;
    }

    @Override
    public boolean visit(WhileStatement whileStmt) {
        final PhpExprNode conditionNode =
                exprVisitor.createExprAst(whileStmt.getCondition(), getCurrentScope());
        final StmtVisitorContext bodyContext =
                new StmtVisitor(this.language).createPhpStmtAst(whileStmt.getBody(),
                        getCurrentScope());
        final StmtListNode stmtListNode = new StmtListNode(bodyContext.stmts);
        setSourceSection(stmtListNode, whileStmt.getBody());
        stmts.add(new PhpWhileNode(conditionNode, stmtListNode));
        return false;
    }

    @Override
    public boolean visit(BreakStatement breakStatement) {
        if (breakStatement.getExpression() != null) {
            throw new UnsupportedOperationException("Break with value not supported");
        }
        final PhpBreakNode breakNode = new PhpBreakNode();
        setSourceSection(breakNode, breakStatement);
        stmts.add(breakNode);
        return false;
    }

    @Override
    public boolean visit(ContinueStatement continueStmt) {
        if (continueStmt.getExpression() != null) {
            throw new UnsupportedOperationException("Continue with value not supported");
        }
        final PhpContinueNode continueNode = new PhpContinueNode();
        setSourceSection(continueNode, continueStmt);
        stmts.add(continueNode);
        return false;
    }

    @Override
    public boolean visit(DoStatement doStatement) {
        final PhpExprNode condition =
                exprVisitor.createExprAst(doStatement.getCondition(), getCurrentScope());
        final StmtVisitorContext bodyContext = new StmtVisitor(this.language)
                .createPhpStmtAst(doStatement.getBody(), getCurrentScope());

        final StmtListNode stmtListNode = new StmtListNode(bodyContext.getStmts());
        setSourceSection(stmtListNode, doStatement.getBody());

        final PhpDoWhileNode doWhileNode = new PhpDoWhileNode(stmtListNode, condition);
        setSourceSection(doWhileNode, doStatement);

        stmts.add(doWhileNode);
        return false;
    }

    @Override
    public boolean visit(ForStatement forStmt) {
        final List<PhpExprNode> initializers =
                visitExpressions(this.exprVisitor, getCurrentScope(), forStmt.initializers());
        final List<PhpExprNode> conditions =
                visitExpressions(this.exprVisitor, getCurrentScope(), forStmt.conditions());
        final List<PhpExprNode> updaters =
                visitExpressions(this.exprVisitor, getCurrentScope(), forStmt.updaters());
        final StmtVisitorContext bodyCtx = new StmtVisitor(this.language)
                .createPhpStmtAst(forStmt.getBody(), getCurrentScope());
        final PhpForWhileNode forNode =
                new PhpForWhileNode(initializers, conditions, updaters, bodyCtx.stmts);

        setSourceSection(forNode, forStmt);
        stmts.add(forNode);

        return false;
    }

    private List<PhpExprNode> visitExpressions(ExprVisitor visitor,
                                               ParseScope scope,
                                               List<Expression> exprs) {
        List<PhpExprNode> nodes = new LinkedList<>();
        for (Expression e : exprs) {
            nodes.add(visitor.createExprAst(e, scope));
        }
        return nodes;
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

