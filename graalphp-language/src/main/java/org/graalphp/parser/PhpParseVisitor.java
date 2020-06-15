package org.graalphp.parser;

import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.controlflow.PhpReturnNode;
import org.graalphp.nodes.controlflow.PhpStmtListNode;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class PhpParseVisitor extends HierarchicalVisitor {

    // TODO: do we need to store this?
    private Source source;

    /**
     * current expression in tree
     **/
    PhpExprNode currExprStmt = null;

    List<PhpStmtNode> astStmts = new LinkedList<>();

    private static final Logger LOG = PhpLogger
            .getLogger(PhpParseVisitor.class.getSimpleName());

    public PhpParseVisitor(Source source) {
        this.source = source;
    }

    public PhpParseVisitor() {
        this.source = null;
    }

    public PhpParseResult createGraalAst(Program pgm) {
        pgm.accept(this);
        return new PhpParseResult(astStmts);
    }

    private SourceSection createSourceSection(ASTNode node) {
        int start = node.getStart();
        int len = node.getLength();
        if (len > 0) {
            return source.createSection(start, len);
        } else {
            return source.createSection(start);
        }
    }

    private void acceptExprStatement(final Expression e) {
        currExprStmt = null;
        e.accept(this);
        assert (currExprStmt != null);

    }

    // --------------- visitor methods -------------------

    @Override
    public boolean visit(Program program) {
        astStmts = new LinkedList<>();
        for (Statement stmt : program.statements()) {
            stmt.accept(this);
        }
        LOG.info(astStmts.toString());
        // XXX: not interested in program#comments
        return false;
    }

    // ---------------- expression statements --------------------
    @Override
    public boolean visit(ExpressionStatement expressionStatement) {
        currExprStmt = null;
        return true;
    }

    @Override
    public void endVisit(ExpressionStatement expressionStatement) {
        assert (currExprStmt != null);
        astStmts.add(currExprStmt);
    }

    @Override
    public boolean visit(InfixExpression expr) {
        PhpExprNode left, right;

        acceptExprStatement(expr.getLeft());
        left = currExprStmt;
        acceptExprStatement(expr.getRight());
        right = currExprStmt;

        LOG.info(left.toString());
        LOG.info(right.toString());

        currExprStmt = createInfixExpression(expr, left, right);
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
        acceptExprStatement(op.getExpression());
        PhpExprNode child = currExprStmt;
        currExprStmt = createUnaryExpression(op, child);

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
                currExprStmt = NumberLiteralFactory.parseInteger(scalar.getStringValue());
                break;
            case Scalar.TYPE_REAL:
                currExprStmt = NumberLiteralFactory.parseFloat(scalar.getStringValue());
                break;
            default:
                hasSource = false;
                LOG.parserEnumerationError("unexpected scalar type: "
                        + scalar.getScalarType());
        }
        if (hasSource) {
            currExprStmt.setSourceSection(scalar.getStart(), scalar.getLength());
        }
        return false;
    }

    // ---------------- assignment expressions --------------------

    @Override
    public boolean visit(Assignment assignment) {
        assignment.getLeftHandSide().accept(this);
        assignment.getRightHandSide().accept(this);
        return super.visit(assignment);
    }
}
