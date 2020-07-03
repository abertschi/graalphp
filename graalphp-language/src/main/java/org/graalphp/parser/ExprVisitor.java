package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.Assignment;
import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.nodes.InfixExpression;
import org.eclipse.php.core.ast.nodes.PostfixExpression;
import org.eclipse.php.core.ast.nodes.PrefixExpression;
import org.eclipse.php.core.ast.nodes.Scalar;
import org.eclipse.php.core.ast.nodes.UnaryOperation;
import org.eclipse.php.core.ast.nodes.Variable;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.binary.PhpAddNode;
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
import org.graalphp.nodes.controlflow.ExprGroupNode;
import org.graalphp.nodes.function.PhpFunctionLookupNode;
import org.graalphp.nodes.function.PhpInvokeNode;
import org.graalphp.nodes.literal.PhpBooleanNode;
import org.graalphp.nodes.literal.PhpLongNode;
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
    private static final String PREFIX_POSTFIX_LOCAL_STORE = "___tmp_postfix";

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
    public boolean visit(PostfixExpression postfixExpression) {
        final String varName = new IdentifierVisitor()
                .getIdentifierName(postfixExpression).getName();
        final PhpExprNode variableNode = initAndAcceptExpr(postfixExpression.getVariable());

        long op = 0;
        switch (postfixExpression.getOperator()) {
            case PostfixExpression.OP_INC:
                op = 1;
                break;
            case PostfixExpression.OP_DEC:
                op = -1;
                break;
            default:
                throw new UnsupportedOperationException("Unexpected value: "
                        + postfixExpression.getOperator() + ": " + postfixExpression);
        }

        /*
         * we implement postfix semantic with temporary store
         * $a++ <=> $_tmp = $a; $a = $a + 1; return $_tmp
         */

        // TODO: investigate if a node based approach is faster

        // tmp store
        final String tmp_var = PREFIX_POSTFIX_LOCAL_STORE + varName;
        PhpExprNode tmpStoreNode = createLocalAssignment(scope, tmp_var, variableNode, null);

        // do addition
        final PhpAddNode postfixNode = PhpAddNodeGen.create(variableNode, new PhpLongNode(op));
        setSourceSection(postfixNode, postfixExpression);

        // assign addition
        final PhpExprNode incrementNode = createLocalAssignment(scope, varName, postfixNode, null);
        setSourceSection(incrementNode, postfixExpression);

        // lookup tmp store
        final PhpExprNode lookupTmp = ReadLocalVarNodeGen.create(scope.getVars().get(tmp_var));
        setSourceSection(lookupTmp, postfixExpression);

        final ExprGroupNode exprGroup =
                new ExprGroupNode(new PhpExprNode[]{tmpStoreNode, incrementNode, lookupTmp});

        setSourceSection(exprGroup, postfixExpression);
        currExpr = exprGroup;

        return false;
    }

    @Override
    public boolean visit(PrefixExpression prefixExpression) {
        // counter = ++$a
        // $a = $a + 1; counter = $a;
        final String varName =
                new IdentifierVisitor().getIdentifierName(prefixExpression).getName();
        final PhpExprNode variableNode = initAndAcceptExpr(prefixExpression.getVariable());

        long op = 0;
        switch (prefixExpression.getOperator()) {
            case PrefixExpression.OP_INC:
                op = 1;
                break;
            case PrefixExpression.OP_DEC:
                op = -1;
                break;
            default:
                throw new UnsupportedOperationException("Unexpected value: "
                        + prefixExpression.getOperator() + ": " + prefixExpression);
        }
        final PhpAddNode prefixNode = PhpAddNodeGen.create(variableNode, new PhpLongNode(op));
        setSourceSection(prefixNode, prefixExpression);

        final PhpExprNode assignment = createLocalAssignment(scope, varName, prefixNode, null);
        setSourceSection(assignment, prefixExpression);

        currExpr = assignment;
        return false;
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
        final PhpExprNode assignNode = createLocalAssignment(scope, dest, source, null);
        setSourceSection(assignNode, ass);
        currExpr = assignNode;
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
        final PhpExprNode assign = WriteLocalVarNodeGen.create(source, frameSlot);
        return assign;
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
}
