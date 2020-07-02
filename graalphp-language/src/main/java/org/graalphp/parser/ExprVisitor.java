package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.ArrayCreation;
import org.eclipse.php.core.ast.nodes.Assignment;
import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.nodes.InfixExpression;
import org.eclipse.php.core.ast.nodes.Scalar;
import org.eclipse.php.core.ast.nodes.UnaryOperation;
import org.eclipse.php.core.ast.nodes.Variable;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.binary.logical.PhpAndNode;
import org.graalphp.nodes.binary.logical.PhpEqNodeGen;
import org.graalphp.nodes.binary.logical.PhpGeNodeGen;
import org.graalphp.nodes.binary.logical.PhpGtNodeGen;
import org.graalphp.nodes.binary.logical.PhpLeNodeGen;
import org.graalphp.nodes.binary.logical.PhpLtNodeGen;
import org.graalphp.nodes.binary.logical.PhpNeqNodeGen;
import org.graalphp.nodes.binary.logical.PhpOrNode;
import org.graalphp.nodes.function.PhpFunctionLookupNode;
import org.graalphp.nodes.function.PhpInvokeNode;
import org.graalphp.nodes.literal.PhpBooleanNode;
import org.graalphp.nodes.localvar.ReadLocalVarNodeGen;
import org.graalphp.nodes.localvar.WriteLocalVarNodeGen;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class ExprVisitor extends HierarchicalVisitor {

    private static final Logger LOG = PhpLogger.getLogger(ExprVisitor.class.getSimpleName());

    // current expression
    private PhpExprNode currExpr = null;
    private ParseScope scope;

    public ExprVisitor(PhpLanguage language) {
    }

    // XXX: not thread safe
    public PhpExprNode createExprAst(Expression e, ParseScope scope) {
        if (scope == null) {
            new IllegalArgumentException("scope. cant be null");
        }
        currExpr = null;
        this.scope = scope;

        PhpExprNode res = initAndAcceptExpr(e);

        currExpr = null;
        this.scope = null;
        return res;
    }

    private PhpExprNode initAndAcceptExpr(final Expression e) {
        currExpr = null;
        e.accept(this);
        assert (currExpr != null) : "Current expression must be set after an accept phase. " +
                "Unsupported syntax? " + e.toString();
        return currExpr;
    }

    private void setSourceSection(PhpStmtNode target, ASTNode n) {
        target.setSourceSection(n.getStart(), n.getLength());
    }

    @Override
    public boolean visit(InfixExpression expr) {
        final PhpExprNode left, right;
        left = initAndAcceptExpr(expr.getLeft());
        right = initAndAcceptExpr(expr.getRight());

        currExpr = createInfixExpression(expr, left, right);
        return false;
    }

    private PhpExprNode createInfixExpression(final InfixExpression expr,
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
            case InfixExpression.OP_IS_EQUAL:
                result = PhpEqNodeGen.create(left, right);
                break;
            case InfixExpression.OP_IS_NOT_EQUAL:
                result = PhpNeqNodeGen.create(left, right);
                break;
            case InfixExpression.OP_LGREATER:
                result = PhpGtNodeGen.create(left, right);
                break;
            case InfixExpression.OP_RGREATER:
                result = PhpLtNodeGen.create(left, right);
                break;
            case InfixExpression.OP_IS_SMALLER_OR_EQUAL:
                result = PhpLeNodeGen.create(left, right);
                break;
            case InfixExpression.OP_IS_GREATER_OR_EQUAL:
                result = PhpGeNodeGen.create(left, right);
                break;
            case InfixExpression.OP_BOOL_AND:
                result = new PhpAndNode(left, right);
                break;
            case InfixExpression.OP_BOOL_OR:
                result = new PhpOrNode(left, right);
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

    private PhpExprNode createUnaryExpression(final UnaryOperation op, final PhpExprNode child) {
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
            case Scalar.TYPE_STRING:
                final String v = scalar.getStringValue();
                if (NumberLiteralFactory.isBooleanLiteral(v)) {
                    currExpr = new PhpBooleanNode(NumberLiteralFactory.booleanLiteralToValue(v));
                } else {
                    LOG.parserEnumerationError("Strings not yet supported");
                }
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

    // ---------------- local variable lookup --------------------

    @Override
    public boolean visit(Variable variable) {
        assert (currExpr == null);

        if (!(variable.getName() instanceof Identifier)) {
            throw new UnsupportedOperationException("Other variables than identifier not " +
                    "supported");
        }

        // TODO: support global vars
        final String name = ((Identifier) variable.getName()).getName();
        FrameSlot varSlot = scope.getVars().get(name);
        if (varSlot == null) {
            PhpException.undefVariableError(name, null);
        }

        final PhpExprNode varNode = ReadLocalVarNodeGen.create(varSlot);
        setSourceSection(varNode, variable);

        currExpr = varNode;
        return false;
    }

    // ---------------- local variable write --------------------

    @Override
    public boolean visit(Assignment ass) {
        if (!(ass.getLeftHandSide() instanceof Variable)) {
            throw new UnsupportedOperationException("Other variables than identifier not " +
                    "supported");
        }

        final String dest = new IdentifierVisitor()
                .getIdentifierName(ass.getLeftHandSide()).getName();

        final PhpExprNode source = initAndAcceptExpr(ass.getRightHandSide());
        final FrameSlot frameSlot = scope.getFrameDesc().findOrAddFrameSlot(
                dest,
                null,
                FrameSlotKind.Illegal);

        scope.getVars().put(dest, frameSlot);
        final PhpExprNode assignNode = WriteLocalVarNodeGen.create(source, frameSlot);
        setSourceSection(assignNode, ass);

        currExpr = assignNode;
        return false;
    }

    // ---------------- function invocations --------------------

    @Override
    public boolean visit(FunctionInvocation fn) {
        final Identifier fnId =
                new IdentifierVisitor().getIdentifierName(fn.getFunctionName().getName());
        if (fnId == null) {
            throw new UnsupportedOperationException("we dont support function lookup in vars");
        }
        List<PhpExprNode> args = new LinkedList<>();
        for (Expression e : fn.parameters()) {
            final PhpExprNode arg = initAndAcceptExpr(e);
            args.add(arg);
        }

        final PhpFunctionLookupNode lookupNode = new PhpFunctionLookupNode(fnId.getName(), scope);
        setSourceSection(lookupNode, fn);
        final PhpInvokeNode invokeNode = new PhpInvokeNode(args.toArray(new PhpExprNode[0]),
                lookupNode);
        setSourceSection(invokeNode, fn);

        currExpr = invokeNode;
        return false;
    }

    // ---------------- arrays --------------------

    @Override
    public boolean visit(ArrayCreation arrayCreation) {
        if (arrayCreation.isHasArrayKey()) {
            throw new UnsupportedOperationException("Arrays with keys not yet supported");
        }
        arrayCreation.elements();

        return false;
    }

-
}
