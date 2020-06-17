package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpException;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpFunctionLookupNode;
import org.graalphp.nodes.PhpInvokeNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.localvar.PhpReadVarNodeGen;
import org.graalphp.nodes.localvar.PhpWriteVarNodeGen;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class ExprVisitor extends HierarchicalVisitor {

    // current expression
    private PhpExprNode currExpr = null;
    private ParseScope scope;
    private PhpLanguage language;

    private static final Logger LOG = PhpLogger
            .getLogger(ExprVisitor.class.getSimpleName());


    public ExprVisitor(PhpLanguage language) {
        this.language = language;
    }

    // XXX: not thread safe
    // to reduce object creation, call visitExpression
    // on multipl. expressions
    public PhpExprNode createExprAst(Expression e, ParseScope scope) {
        if (scope == null) {
            new IllegalArgumentException("scope. cant be null");
        }
        currExpr = null;
        this.scope = scope;
        e.accept(this);
        PhpExprNode res = currExpr;
        currExpr = null;
        this.scope = null;
        return res;
    }

    private PhpExprNode initAndAcceptExpr(final Expression e) {
        currExpr = null;
        e.accept(this);
        assert (currExpr != null);
        return currExpr;
    }

    private void setSourceSection(PhpStmtNode target, ASTNode n) {
        target.setSourceSection(n.getStart(), n.getLength());
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

    // ---------------- local variable lookup --------------------

    @Override
    public boolean visit(Variable variable) {
        assert (currExpr == null);

        if (!(variable.getName() instanceof Identifier)) {
            throw new UnsupportedOperationException("Other variables than identifier not supported");
        }

        // TODO: support global vars
        final String name = ((Identifier) variable.getName()).getName();
        FrameSlot varSlot = scope.getVars().get(name);
        if (varSlot == null) {
            PhpException.undefVariableError(name, null);
        }

        final PhpExprNode varNode = PhpReadVarNodeGen.create(varSlot);
        setSourceSection(varNode, variable);

        currExpr = varNode;
        return false;
    }

    // ---------------- local variable write --------------------

    @Override
    public boolean visit(Assignment ass) {
        System.out.println(ass.getLeftHandSide());
        if (!(ass.getLeftHandSide() instanceof Variable)) {
            throw new UnsupportedOperationException("Other variables than identifier not supported");
        }

        final String dest = new IdentifierVisitor()
                .getIdentifierName(ass.getLeftHandSide()).getName();

        final PhpExprNode source = initAndAcceptExpr(ass.getRightHandSide());
        final FrameSlot frameSlot = scope.getFrameDesc().findOrAddFrameSlot(
                dest,
                null,
                FrameSlotKind.Illegal);

        scope.getVars().put(dest, frameSlot);
        final PhpExprNode assignNode = PhpWriteVarNodeGen.create(source, frameSlot);
        setSourceSection(assignNode, ass);

        currExpr = assignNode;
        return false;
    }


    // ---------------- function invocations --------------------


    //<FunctionInvocation start='44' length='9'>
    //	<FunctionName start='44' length='3'>
    //		<NamespaceName start='44' length='3' global='false' current='false'>
    //			<Identifier start='44' length='3' name='foo'/>
    //		</NamespaceName>
    //	</FunctionName>
    //	<Parameters>
    //		<Scalar start='48' length='4' type='int' value='1337'/>
    //	</Parameters>
    //</FunctionInvocation>

    @Override
    public boolean visit(FunctionInvocation fn) {
        //TODO:  we dont support function names which are stored in variables yet

        final Identifier fnId = new IdentifierVisitor().getIdentifierName(fn.getFunctionName().getName());
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
        final PhpInvokeNode invokeNode =
                new PhpInvokeNode(args.toArray(new PhpExprNode[0]), lookupNode);
        setSourceSection(invokeNode, fn);

        currExpr = invokeNode;
        return false;
    }
}
