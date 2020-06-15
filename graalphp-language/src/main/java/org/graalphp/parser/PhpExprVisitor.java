package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * @author abertschi
 */
public class PhpExprVisitor extends HierarchicalVisitor {

    private PhpExprNode currExpr = null;

    private static final Logger LOG = PhpLogger
            .getLogger(PhpExprVisitor.class.getSimpleName());

    public PhpExprVisitor() {
    }

    // XXX: not thread safe
    // to reduce object creation, call visitExpression
    // on multipl. expressions
    public PhpExprNode createExprAst(Expression e) {
        currExpr = null;
        e.accept(this);
        PhpExprNode res = currExpr;
        currExpr = null;
        return res;
    }

    private void initAndAcceptExpr(final Expression e) {
        currExpr = null;
        e.accept(this);
        assert (currExpr != null);
    }

    @Override
    public boolean visit(InfixExpression expr) {
        PhpExprNode left, right;

        initAndAcceptExpr(expr.getLeft());
        left = currExpr;
        initAndAcceptExpr(expr.getRight());
        right = currExpr;

        LOG.info(left.toString());
        LOG.info(right.toString());

        currExpr = createInfixExpression(expr, left, right);
        return false;
    }

    PhpExprNode createInfixExpression(final InfixExpression expr,
                                      final PhpExprNode left,
                                      final PhpExprNode right) {
        PhpExprNode result = null;
        boolean exprHasSource = true;

        switch (expr.getOperator()) {
            case InfixExpression.OP_PLUS: /* + */
                result = PhpAddNodeGen.create(left, right);
                break;
            case InfixExpression.OP_MINUS: /* - */
                result = PhpSubNodeGen.create(left, right);
                break;
            case InfixExpression.OP_MUL:
                result = PhpMulNodeGen.create(left, right);
                break;
            case InfixExpression.OP_DIV:
                result = PhpDivNodeGen.create(left, right);
                break;
            default:
                exprHasSource = false;
                LOG.parserEnumerationError("infix expression operand not implemented: " +
                        InfixExpression.getOperator(expr.getOperator()));
        }
        if (exprHasSource) {
            result.setSourceSection(expr.getStart(), expr.getLength());
        }
        return result;
    }


    // ---------------- unary expressions --------------------

    @Override
    public boolean visit(UnaryOperation op) {
        initAndAcceptExpr(op.getExpression());
        PhpExprNode child = currExpr;
        currExpr = createUnaryExpression(op, child);

        return false;
    }

    PhpExprNode createUnaryExpression(final UnaryOperation op, final PhpExprNode child) {
        boolean hasSource = true;
        PhpExprNode node = null;

        switch (op.getOperator()) {
            case UnaryOperation.OP_MINUS:
                node = PhpNegNodeGen.create(child);
                break;
            default:
                hasSource = false;
                LOG.parserEnumerationError("unary expression operand not implemented: " +
                        InfixExpression.getOperator(op.getOperator()));
        }
        if (hasSource) {
            node.setSourceSection(op.getStart(), op.getLength());
        }
        return node;
    }

    // ---------------- scalar expressions --------------------

    @Override
    public boolean visit(Scalar scalar) {
        boolean hasSource = true;
        switch (scalar.getScalarType()) {
            case Scalar.TYPE_INT:
                currExpr = NumberLiteralFactory.parseInteger(scalar.getStringValue());
                break;
            case Scalar.TYPE_REAL:
                currExpr = NumberLiteralFactory.parseFloat(scalar.getStringValue());
                break;
            default:
                hasSource = false;
                LOG.parserEnumerationError("unexpected scalar type: "
                        + scalar.getScalarType());
        }
        if (hasSource) {
            currExpr.setSourceSection(scalar.getStart(), scalar.getLength());
        }
        return false;
    }
}
