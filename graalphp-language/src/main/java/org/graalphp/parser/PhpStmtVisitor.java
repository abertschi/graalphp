package org.graalphp.parser;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.controlflow.PhpFnRootNode;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author abertschi
 */
public class PhpStmtVisitor extends HierarchicalVisitor {

    private static final Logger LOG = PhpLogger
            .getLogger(PhpStmtVisitor.class.getSimpleName());

    private final PhpLanguage language;
    private List<PhpStmtNode> stmts;
    private Map<String, RootCallTarget> functions;
    private PhpExprVisitor exprVisitor;

    // tmp storage for walking
    private FrameDescriptor currFnFrameDescriptor;

    public PhpStmtVisitor(PhpLanguage lang) {
        this.exprVisitor = new PhpExprVisitor();
        this.language = lang;
    }

    public PhpStmtVisitorContext createPhpAst(Program p) {
        this.stmts = new LinkedList<>();
        this.functions = new HashMap<>();
        for (Statement s : p.statements()) {
            s.accept(this);
        }

        PhpStmtVisitorContext ctx = new PhpStmtVisitorContext(stmts, functions);
        this.stmts = null;
        this.functions = null;
        return ctx;
    }

    public PhpStmtVisitorContext createPhpStmtAst(Statement stmt) {
        this.stmts = new LinkedList<>();
        this.functions = new HashMap<>();
        stmt.accept(this);

        PhpStmtVisitorContext ctx = new PhpStmtVisitorContext(stmts, functions);
        this.stmts = null;
        this.functions = null;
        return ctx;
    }

    // ---------------- expression statements --------------------

    @Override
    public boolean visit(ExpressionStatement expressionStatement) {
        PhpExprNode phpExpr =
                exprVisitor.createExprAst(expressionStatement.getExpression());

        if (phpExpr == null) {
            LOG.info("exprVisitor.createExprAst returned null. " +
                    "This is fine as long as not all features impl. " + expressionStatement.toString());
        } else {
            stmts.add(phpExpr);
        }
        return false;
    }

    // ---------------- function definition --------------------

    @Override
    public boolean visit(FunctionDeclaration fnParse) {
        // TODO: ignore nullable
        // TODO: ignore return Type
        final String fnName = fnParse.getFunctionName().getName();
        final PhpFnRootNode fnRoot;
        this.currFnFrameDescriptor = new FrameDescriptor();

        for (ASTNode node : fnParse.formalParameters()) {
            // TODO: integrate formal params
            node.accept(this);
        }
        if (fnParse.getBody() != null) {
            final PhpStmtVisitor fnVisitor = new PhpStmtVisitor(this.language);
            final PhpStmtVisitorContext body = fnVisitor.createPhpStmtAst(fnParse.getBody());
            fnRoot = PhpFnRootNode.createFromStmts(language, currFnFrameDescriptor, fnName,
                    body.stmts);
            functions.putAll(body.functions);
        } else {
            fnRoot = PhpFnRootNode.createWithoutBody(language, currFnFrameDescriptor, fnName);
        }
        functions.put(fnName, Truffle.getRuntime().createCallTarget(fnRoot));
        LOG.info("create call target for " + fnName);

        return false;
    }

    @Override
    public boolean visit(FormalParameter parm) {
        // LOG.finest("param: " + parm.getParameterName().toString());
        return false;
    }


    /**
     * Represents return object of ast walking
     **/
    public static class PhpStmtVisitorContext {

        public final List<PhpStmtNode> stmts;
        public final Map<String, RootCallTarget> functions;

        public PhpStmtVisitorContext(List<PhpStmtNode> stmts,
                                     Map<String, RootCallTarget> functions) {
            this.stmts = stmts;
            this.functions = functions;
        }

        @Override
        public String toString() {
            return "PhpStmtVisitorContext{" +
                    "stmts=" + stmts +
                    ", functions=" + functions +
                    '}';
        }
    }
}

