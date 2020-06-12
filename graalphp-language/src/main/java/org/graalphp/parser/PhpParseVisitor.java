package org.graalphp.parser;

import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.literal.NumberLiteralFactory;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class PhpParseVisitor extends HierarchicalVisitor {

    private Source source;

    /** current expression in tree **/
    PhpExprNode astExprStatement = null;
    List<PhpExprNode> astStmts = new LinkedList<>();

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
        PhpParseResult res = new PhpParseResult(astStmts);
        return res;
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

    // --------------- visitor methods -------------------

    @Override
    public boolean visit(Program program) {
        astStmts = new LinkedList<>();
        for(Statement stmt: program.statements()) {
            stmt.accept(this);
        }
        LOG.info(astStmts.toString());
        // XXX: not interested in program#comments
        return false;
    }
    // ---------------- expression statements --------------------
    @Override
    public boolean visit(ExpressionStatement expressionStatement) {
        astExprStatement = null;
        return true;
    }

    @Override
    public void endVisit(ExpressionStatement expressionStatement) {
        assert(astExprStatement != null);
        astStmts.add(astExprStatement);
    }

    @Override
    public boolean visit(InfixExpression expr) {
        PhpExprNode left, right;

        astExprStatement = null;
        expr.getLeft().accept(this);
        assert (astExprStatement != null);
        left = astExprStatement;

        expr.getRight().accept(this);
        assert (astExprStatement != null);
        right = astExprStatement;

        LOG.info(left.toString());
        LOG.info(right.toString());

        boolean exprHasSource = true;
        switch (expr.getOperator()) {
            case InfixExpression.OP_PLUS: /* + */
                astExprStatement = PhpAddNodeGen.create(left, right);
                break;
            case InfixExpression.OP_MINUS: /* - */
                astExprStatement = PhpSubNodeGen.create(left, right);
                break;
            case InfixExpression.OP_MUL:
                astExprStatement = PhpMulNodeGen.create(left, right);
                break;
            case InfixExpression.OP_DIV:
                astExprStatement = PhpDivNodeGen.create(left, right);
                break;
            default:
                exprHasSource = false;
                LOG.parserEnumerationError("infix expression operand not implemented: " +
                        InfixExpression.getOperator(expr.getOperator()));
        }
        if (exprHasSource) {
            astExprStatement.setSourceSection(expr.getStart(), expr.getLength());
        }
        return false;
    }


    // ---------------- unary expressions --------------------


    public void acceptExprStatement(Expression e){
        astExprStatement = null;
        e.accept(this);
        assert (astExprStatement != null);
    }
    @Override
    public boolean visit(UnaryOperation op) {
        acceptExprStatement(op.getExpression());
        PhpExprNode child = astExprStatement;
        boolean hasSource = true;

        switch(op.getOperator()) {
            case UnaryOperation.OP_MINUS:
                 astExprStatement = PhpNegNodeGen.create(child);
                break;

            default:
                hasSource = false;
                LOG.parserEnumerationError("unary expression operand not implemented: " +
                        InfixExpression.getOperator(op.getOperator()));
        }
        if (hasSource) {
            astExprStatement.setSourceSection(op.getStart(), op.getLength());
        }
        return false;
    }

    @Override
    public boolean visit(Scalar scalar) {
        switch (scalar.getScalarType()) {
            case Scalar.TYPE_INT:
                astExprStatement = NumberLiteralFactory.parseNumber(scalar.getStringValue());
                astExprStatement.setSourceSection(scalar.getStart(), scalar.getLength());
                break;
            default:
                LOG.parserEnumerationError("unexpected scalar type: "
                        + scalar.getScalarType());
        }
        return true;
    }

    @Override
    public void endVisit(Scalar scalar) {
        super.endVisit(scalar);
    }

    @Override
    public void endVisit(InfixExpression infixExpression) {
        super.endVisit(infixExpression);
    }


    @Override
    public boolean visit(Assignment assignment) {
        assignment.getLeftHandSide().accept(this);
        assignment.getRightHandSide().accept(this);
        return super.visit(assignment);
    }
}
