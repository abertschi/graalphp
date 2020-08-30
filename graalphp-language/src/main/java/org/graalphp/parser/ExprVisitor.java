package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.ArrayAccess;
import org.eclipse.php.core.ast.nodes.ArrayCreation;
import org.eclipse.php.core.ast.nodes.ArrayElement;
import org.eclipse.php.core.ast.nodes.Assignment;
import org.eclipse.php.core.ast.nodes.ConditionalExpression;
import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.FunctionInvocation;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.nodes.InfixExpression;
import org.eclipse.php.core.ast.nodes.ParenthesisExpression;
import org.eclipse.php.core.ast.nodes.PostfixExpression;
import org.eclipse.php.core.ast.nodes.PrefixExpression;
import org.eclipse.php.core.ast.nodes.Reference;
import org.eclipse.php.core.ast.nodes.Scalar;
import org.eclipse.php.core.ast.nodes.UnaryOperation;
import org.eclipse.php.core.ast.nodes.Variable;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.PhpLanguage;
import org.graalphp.builtins.language.PrintArgsBuiltin;
import org.graalphp.builtins.language.PrintArgsBuiltinNodeGen;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.array.ArrayReadNodeGen;
import org.graalphp.nodes.array.ArrayWriteNodeGen;
import org.graalphp.nodes.array.ExecuteValuesNode;
import org.graalphp.nodes.array.NewArrayInitialValuesNodeGen;
import org.graalphp.nodes.array.NewArrayNode;
import org.graalphp.nodes.assign.FunctionAssignmentBehaviorNode;
import org.graalphp.nodes.binary.PhpAddNodeGen;
import org.graalphp.nodes.binary.PhpDivNodeGen;
import org.graalphp.nodes.binary.PhpMulNodeGen;
import org.graalphp.nodes.binary.PhpRightShiftNodeGen;
import org.graalphp.nodes.binary.PhpShiftLeftNodeGen;
import org.graalphp.nodes.binary.PhpSubNodeGen;
import org.graalphp.nodes.binary.logical.PhpAndNode;
import org.graalphp.nodes.binary.logical.PhpEqNodeGen;
import org.graalphp.nodes.binary.logical.PhpGeNodeGen;
import org.graalphp.nodes.binary.logical.PhpGtNodeGen;
import org.graalphp.nodes.binary.logical.PhpLeNodeGen;
import org.graalphp.nodes.binary.logical.PhpLtNodeGen;
import org.graalphp.nodes.binary.logical.PhpNeqNodeGen;
import org.graalphp.nodes.binary.logical.PhpOrNode;
import org.graalphp.nodes.controlflow.PhpIfInlineNode;
import org.graalphp.nodes.function.PhpFunctionLookupNode;
import org.graalphp.nodes.function.PhpInvokeNode;
import org.graalphp.nodes.literal.PhpBooleanNode;
import org.graalphp.nodes.localvar.ReadLocalVarNodeGen;
import org.graalphp.nodes.unary.PhpNegNodeGen;
import org.graalphp.nodes.unary.PhpNotNode;
import org.graalphp.nodes.unary.PhpPosNodeGen;
import org.graalphp.nodes.unary.PostfixArithmeticNode;
import org.graalphp.nodes.unary.PostfixArithmeticNodeGen;
import org.graalphp.nodes.unary.PrefixArithmeticNode;
import org.graalphp.nodes.unary.PrefixArithmeticNodeGen;
import org.graalphp.runtime.PhpUnsetNode;
import org.graalphp.runtime.PhpUnsetNodeGen;
import org.graalphp.runtime.assign.AssignRuntimeFactory;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
public class ExprVisitor extends HierarchicalVisitor {

    private static final Logger LOG = PhpLogger.getLogger(ExprVisitor.class.getSimpleName());

    private static final String UNSET_OPERATOR = "unset";

    // current expression
    private PhpExprNode currExpr = null;
    private ParseScope scope;

    public ExprVisitor(PhpLanguage language) {
    }

    // XXX: not thread safe
    public PhpExprNode createExprAst(Expression e, ParseScope scope) {
        if (scope == null) {
            throw new IllegalArgumentException("scope. cant be null");
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

    private <T extends PhpStmtNode> T setSource(T target, ASTNode n) {
        if (!target.hasSourceSection()) {
            target.setSourceSection(n.getStart(), n.getLength());
        }
        return target;
    }

    @Override
    public boolean visit(PostfixExpression postfixExpression) {
        final String varName = new IdentifierVisitor()
                .getIdentifierName(postfixExpression).getName();

        int op = 0;
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

        final FrameSlot frameSlot = scope.getFrameDesc()
                .findOrAddFrameSlot(varName, null, FrameSlotKind.Illegal);

        PostfixArithmeticNode postfixNode = PostfixArithmeticNodeGen.create(frameSlot, op);
        setSource(postfixNode, postfixExpression);
        currExpr = postfixNode;

        return false;
    }

    @Override
    public boolean visit(PrefixExpression prefixExpression) {
        // counter = ++$a
        // $a = $a + 1; counter = $a;
        final String varName =
                new IdentifierVisitor().getIdentifierName(prefixExpression).getName();
        int op = 0;
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
        final FrameSlot frameSlot = scope.getFrameDesc()
                .findOrAddFrameSlot(varName, null, FrameSlotKind.Illegal);

        final PrefixArithmeticNode prefixNode = PrefixArithmeticNodeGen.create(frameSlot, op);
        setSource(prefixNode, prefixExpression);
        currExpr = prefixNode;
        return false;
    }

    @Override
    public boolean visit(InfixExpression expr) {
        final PhpExprNode left, right;
        left = initAndAcceptExpr(expr.getLeft());
        right = initAndAcceptExpr(expr.getRight());
        BinaryOperators op = BinaryOperators.fromInfixExpressionOperator(expr.getOperator());
        currExpr = createBinaryOperationNode(op, left, right, expr);
        return false;
    }

    private PhpExprNode createBinaryOperationNode(final BinaryOperators op,
                                                  final PhpExprNode left,
                                                  final PhpExprNode right,
                                                  final Expression sourceSection) {
        PhpExprNode result = null;
        switch (op) {
            case OP_PLUS: /* + */
                result = PhpAddNodeGen.create(left, right);
                break;
            case OP_MINUS: /* - */
                result = PhpSubNodeGen.create(left, right);
                break;
            case OP_MUL:
                result = PhpMulNodeGen.create(left, right);
                break;
            case OP_DIV:
                result = PhpDivNodeGen.create(left, right);
                break;
            case OP_IS_EQUAL:
                result = PhpEqNodeGen.create(left, right);
                break;
            case OP_IS_NOT_EQUAL:
                result = PhpNeqNodeGen.create(left, right);
                break;
            case OP_LGREATER:
                result = PhpGtNodeGen.create(left, right);
                break;
            case OP_RGREATER:
                result = PhpLtNodeGen.create(left, right);
                break;
            case OP_IS_SMALLER_OR_EQUAL:
                result = PhpLeNodeGen.create(left, right);
                break;
            case OP_IS_GREATER_OR_EQUAL:
                result = PhpGeNodeGen.create(left, right);
                break;
            case OP_BOOL_AND:
                result = new PhpAndNode(left, right);
                break;
            case OP_BOOL_OR:
                result = new PhpOrNode(left, right);
                break;
            case OP_SL:
                result = PhpShiftLeftNodeGen.createAndConvertToLong(left, right);
                break;
            case OP_SR:
                result = PhpRightShiftNodeGen.createAndConvertToLong(left, right);
                break;
            case OP_NOT_IMPLEMENTED:
                // XXX: Not yet all operators implemented
            default:
                String msg = "infix expr operand not implemented: " + op + "/" + sourceSection;
                throw new UnsupportedOperationException(msg);
        }
        setSource(result, sourceSection);
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
        PhpExprNode node = null;
        switch (op.getOperator()) {
            case UnaryOperation.OP_MINUS:
                node = PhpNegNodeGen.create(child);
                break;
            case UnaryOperation.OP_PLUS:
                node = PhpPosNodeGen.create(child);
                break;
            case UnaryOperation.OP_NOT:
                node = PhpNotNode.createAndConvertToBoolean(child);
                break;
            default:
                throw new UnsupportedOperationException("unary expression operand not implemented: "
                        + op);
        }
        node.setSourceSection(op.getStart(), op.getLength());
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
                    throw new UnsupportedOperationException("Strings not yet supported: " + scalar);
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
                    "supported" + variable);
        }
        // TODO: we currently do not support global variables
        final String name = ((Identifier) variable.getName()).getName();
        FrameSlot varSlot = scope.getVars().get(name);
        if (varSlot == null) {
            PhpException.undefVariableError(name, null);
        }
        final PhpExprNode varNode = ReadLocalVarNodeGen.create(varSlot);
        setSource(varNode, variable);

        currExpr = varNode;
        return false;
    }

    // ---------------- local variable write --------------------

    @Override
    public boolean visit(Assignment ass) {
        if (!(ass.getLeftHandSide() instanceof Variable)) {
            throw new UnsupportedOperationException("Other vars. than Variable not supported:" + ass);
        }
        currExpr = createAssignmentNode(ass);
        return false;
    }

    private PhpExprNode createAssignmentNode(Assignment ass) {
        final PhpExprNode rhsNode = createAssignmentEvalRhs(ass);

        // Array lookup expression $A[$val] = ...
        if (ass.getLeftHandSide() instanceof ArrayAccess) {
            // XXX: $A[...] = X will return X not Array
            final ArrayAccess lhs = (ArrayAccess) ass.getLeftHandSide();
            return createArrayWriteNode(lhs.getName(), lhs.getIndex(), rhsNode, ass);
        } else {
            // variable expression $A = ...
            final String assignName = new IdentifierVisitor()
                    .getIdentifierName(ass.getLeftHandSide()).getName();

            // XXX: We dont need local assignment if we write into arrays
            // we already have their reference
            return VisitorHelpers.createLocalAssignment(scope, assignName, rhsNode, null, ass);
        }
    }

    private boolean isAssignmentByReference(Assignment ass) {
        return ass.getRightHandSide() instanceof Reference;
    }

    private PhpExprNode createAssignmentEvalRhs(Assignment ass) {
        PhpExprNode rhsNode;
        if (ass.getOperator() != Assignment.OP_EQUAL) {
            // expression: $a += ...
            final BinaryOperators rhsOpType =
                    BinaryOperators.fromAssignmentOperator(ass.getOperator());
            final PhpExprNode rhsOp1 = initAndAcceptExpr(ass.getLeftHandSide());
            final PhpExprNode rhsOp2 = initAndAcceptExpr(ass.getRightHandSide());
            rhsNode = createBinaryOperationNode(rhsOpType, rhsOp1, rhsOp2, ass);
        } else {
            // expression: $a = ...
            rhsNode = initAndAcceptExpr(ass.getRightHandSide());
        }
        setSource(rhsNode, ass);
        rhsNode = copyBehaviorForAssignment(rhsNode, ass);
        return rhsNode;
    }

    // optimization
    private PhpExprNode copyBehaviorForAssignment(PhpExprNode source, Assignment ass) {
        boolean enableOptimization = true;
        // XXX: We potentially copy arrays twice if we assign by value
        // from function return (1st copy from invoke node) and
        // copy by value again here in order to store in variable
        // FunctionAssignmentBehaviorNode skips copy by value
        // if we immediately assign function result to a variable.
        // We do not know function definition yet. as it may appear below assignment
        // this is why we have to look up function at runtime
        if (enableOptimization && ass.getRightHandSide() instanceof FunctionInvocation) {
            return copyBehaviorForAssignmentOptimized(source, ass);
        } else {
            return copyBehaviorForAssignmentDefault(source, ass);
        }
    }

    // create a copy if we dont assign by reference and are not assigning function result
    // $A [&] = foo();
    private PhpExprNode copyBehaviorForAssignmentOptimized(PhpExprNode source, Assignment ass) {
        final Identifier fnName = getFunctionName((FunctionInvocation) ass.getRightHandSide());
        final PhpFunctionLookupNode lookupNode =
                setSource(new PhpFunctionLookupNode(fnName.getName(), scope), ass);

        final PhpExprNode node = new FunctionAssignmentBehaviorNode(
                isAssignmentByReference(ass),
                lookupNode,
                source);
        return setSource(node, ass);
    }

    // default behavior, create a copy if we dont assign by reference
    // $A [&] = ... // not a function
    private PhpExprNode copyBehaviorForAssignmentDefault(PhpExprNode source, Assignment ass) {
        return setSource(AssignRuntimeFactory
                .createForwardValueNode(isAssignmentByReference(ass), source), ass);
    }

    // create Node which writes value at index into array, $A[val] = ...
    private PhpExprNode createArrayWriteNode(Expression arrayTarget,
                                             Expression arrayIndex,
                                             PhpExprNode value,
                                             Expression sourceSection) {
        final PhpExprNode arrayTargetNode = initAndAcceptExpr(arrayTarget);
        final PhpExprNode arrayIndexNode = initAndAcceptExpr(arrayIndex);
        final PhpExprNode arrayWriteNode =
                ArrayWriteNodeGen.create(arrayTargetNode, arrayIndexNode, value);
        setSource(arrayWriteNode, sourceSection);
        return arrayWriteNode;
    }

    // ---------------- function invocations --------------------

    private Identifier getFunctionName(FunctionInvocation invoke) {
        final Identifier fnId = new IdentifierVisitor()
                .getIdentifierName(invoke.getFunctionName().getName());
        if (fnId == null) {
            throw new UnsupportedOperationException("we dont support function lookup in vars" + invoke);
        }
        return fnId;
    }

    @Override
    public boolean visit(FunctionInvocation fn) {
        final Identifier fnId = getFunctionName(fn);

        // builtin functions which need special care
        if (isLanguageFunctionOperator(fnId.getName())) {
            currExpr = buildLanguageFunctionOperator(fn, fnId.getName());
            return false;
        }

        List<PhpExprNode> args = new LinkedList<>();
        for (Expression e : fn.parameters()) {
            final PhpExprNode arg = initAndAcceptExpr(e);
            args.add(arg);
        }
        final PhpFunctionLookupNode lookupNode = new PhpFunctionLookupNode(fnId.getName(), scope);
        setSource(lookupNode, fn);

        final PhpInvokeNode invokeNode =
                new PhpInvokeNode(args.toArray(new PhpExprNode[0]), lookupNode);

        setSource(invokeNode, fn);
        currExpr = invokeNode;
        return false;
    }

    // some builtin functions cannot be treated as regular functions
    private boolean isLanguageFunctionOperator(String name) {
        return name.equals(UNSET_OPERATOR) || name.equals(PrintArgsBuiltin.NAME);
    }

    private PhpExprNode buildLanguageFunctionOperator(FunctionInvocation fn, String name) {
        switch (name) {
            case UNSET_OPERATOR:
                List<String> names = new LinkedList<>();
                for (Expression e : fn.parameters()) {
                    String id = new ExpectsSingleVariable().resolveName(e);
                    if (id == null) {
                        String msg = "No identifier found in unset operator: " + e + " / " + fn;
                        throw new PhpException(msg, null);
                    }
                    names.add(id);
                }
                final PhpUnsetNode unsetNode =
                        PhpUnsetNodeGen.create(names.toArray(new String[names.size()]), scope);
                setSource(unsetNode, fn);
                return unsetNode;

            /*
             * Builtin function to print a string along with values
             * Used to run benchmarks. Obsolete once we support strings
             */
            case PrintArgsBuiltin.NAME:
                if (fn.parameters().size() < 1 || !(fn.parameters().get(0) instanceof Scalar)) {
                    String msg = PrintArgsBuiltin.NAME + "needs string as first arg";
                    throw new IllegalArgumentException(msg);
                }
                String title = ((Scalar) fn.parameters().get(0)).getStringValue();
                final List<PhpExprNode> nodeArgs = new LinkedList<>();
                for (int i = 1; i < fn.parameters().size(); i++) {
                    nodeArgs.add(initAndAcceptExpr(fn.parameters().get(i)));
                }
                final ExecuteValuesNode executeValsNode = new ExecuteValuesNode(nodeArgs);
                setSource(executeValsNode, fn);
                final PrintArgsBuiltin printNode =
                        PrintArgsBuiltinNodeGen.create(executeValsNode, title);
                setSource(printNode, fn);
                return printNode;
            default:
                throw new IllegalArgumentException("unsupported language function invocation: " + name);
        }
    }

    // ---------------- arrays --------------------

    @Override
    public boolean visit(ArrayCreation arrayCreation) {
        List<PhpExprNode> arrayInitVals = new LinkedList<>();
        for (ArrayElement e : arrayCreation.elements()) {
            final Expression key = e.getKey(); // TODO: no support for keys yet
            if (key != null) {
                throw new UnsupportedOperationException("Arrays with keys not yet supported");
            }
            final Expression val = e.getValue();
            arrayInitVals.add(initAndAcceptExpr(val));
        }

        // XXX: We currently support long arrays by default and generalize if needed
        final PhpExprNode newArrayNode;
        if (arrayInitVals.size() == 0) {
            newArrayNode = new NewArrayNode();
        } else {
            newArrayNode = NewArrayInitialValuesNodeGen.create(arrayInitVals);
        }
        setSource(newArrayNode, arrayCreation);
        currExpr = newArrayNode;
        return false;
    }

    @Override
    public boolean visit(ArrayAccess arrayAccess) {
        if (arrayAccess.getArrayType() == ArrayAccess.VARIABLE_HASHTABLE) {
            throw new UnsupportedOperationException("ArrayAccess.VARIABLE_HASHTABLE not supported" +
                    " in: " + arrayAccess.toString());
        }
        final PhpExprNode target = initAndAcceptExpr(arrayAccess.getName());
        final PhpExprNode index = initAndAcceptExpr(arrayAccess.getIndex());
        currExpr = ArrayReadNodeGen.create(target, index);
        setSource(currExpr, arrayAccess);

        return false;
    }

    @Override
    public boolean visit(ParenthesisExpression parenthesisExpression) {
        currExpr = initAndAcceptExpr(parenthesisExpression.getExpression());
        return false;
    }

    // ----------------- inline if/else ----------------
    @Override
    public boolean visit(ConditionalExpression expr) {
        // XXX: We currently only support A ? B : C; conditional expressions
        // XXX: B may not exist, in that case we have a coalesce like operator

        // XXX: Important, PHP's conditional expression is left-associative
        // this causes a lot of confusion.
        // True ? 1 : False ? 2 : 3 will return 2 not 1!
        // this is due to the left associativity;
        // (True ? 1 : False) ? 2 : 3
        // as opposite to right to left associativity:
        // True ? 1 : (False ? 2 : 3)

        // :: XXX: Behavior, print a warning and force user to add ()
        // https://wiki.php.net/rfc/ternary_associativity
        // The feature will drop in the next release
        // behavior is very un-intuitive which is why we throw error
        // and force user to use parenthesis

        if (expr.getOperatorType() != ConditionalExpression.OP_TERNARY) {
            String msg = "Conditional Expression not supported: " + expr;
            throw new UnsupportedOperationException(msg);
        }
        if (expr.getIfTrue() == null) {
            String msg = "Conditional Expression with coalesce behavior not supported: " + expr;
            throw new UnsupportedOperationException(msg);
        }
        if (expr.getIfFalse() instanceof ConditionalExpression) {
            String msg = "PHP Deprecated:  Unparenthesized `a ? b : c ? d : e` is deprecated. " +
                    "Use either `(a ? b : c) ? d : e` or `a ? b : (c ? d : e) in " + expr;
            throw new UnsupportedOperationException(msg);
        }
        final PhpIfInlineNode node = new PhpIfInlineNode(
                initAndAcceptExpr(expr.getCondition()),
                initAndAcceptExpr(expr.getIfTrue()),
                initAndAcceptExpr(expr.getIfFalse()));

        setSource(node, expr);
        currExpr = node;
        return false;
    }
}
