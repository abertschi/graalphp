package org.graalphp.parser;

import com.oracle.truffle.api.RootCallTarget;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
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

    public static class PhpStmtVisitorContext {

        public final List<PhpStmtNode> stmts;
        public final Map<String, RootCallTarget> functions;

        public PhpStmtVisitorContext(List<PhpStmtNode> stmts,
                                     Map<String, RootCallTarget> functions) {
            this.stmts = stmts;
            this.functions = functions;
        }
    }

    private static final Logger LOG = PhpLogger
            .getLogger(PhpStmtVisitor.class.getSimpleName());

    private List<PhpStmtNode> stmts;
    private Map<String, RootCallTarget> functions;
    private PhpExprVisitor exprVisitor;


    public PhpStmtVisitor() {
        this.exprVisitor = new PhpExprVisitor();
    }

    public PhpStmtVisitorContext createPhpAst(Program p) {
        this.stmts = new LinkedList<>();
        this.functions = new HashMap<>();
        for (Statement s : p.statements()) {
            p.accept(this);
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
        stmts.add(phpExpr);
        return false;
    }

    // ---------------- function definition --------------------

    @Override
    public boolean visit(FunctionDeclaration fn) {
        // TODO : we ignore isNullable for now
        String name = fn.getFunctionName().getName();
        String returnType = fn.getReturnType() != null ? fn.getReturnType().toString() : null;

        for (ASTNode node : fn.formalParameters()) {
            node.accept(this);
        }
        if (fn.getBody() != null) {
            fn.getBody().accept(this);
        }
        return false;
    }

    @Override
    public boolean visit(FormalParameter parm) {
        return true;
    }
}
