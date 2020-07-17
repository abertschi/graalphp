package org.graalphp.nodes.assign;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.PhpLanguage;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.function.PhpFunctionLookupNode;
import org.graalphp.types.PhpFunction;

/**
 * We typically have a forwarding node in front of invoke nodes.
 * Forwarding nodes copy values according to specified semantics. Either by-value or by-reference.
 * If we return by value, we may copy the return value twice if we assign a function result to a
 * variable.
 * This node skips copy by-value if the function returns by-value already.
 * Function needs to be looked up as it may not be available yet once we reach assignment.
 * <p>
 * <p>
 * According to specification:
 * 1. if we assign without & we always assign by value
 * 2. we only assign by reference if both assignment and function use by-ref notation
 *
 * @author abertschi
 * @see AssignSemanticNode
 */
public final class FunctionAssignmentBehaviorNode extends PhpExprNode {

    /**
     * Source value to forward
     */
    @Child
    private PhpExprNode sourceNode;

    /**
     * Node to lookup PhpFunction at runtime
     */
    @Child
    private PhpFunctionLookupNode lookupFunctionNode;

    /**
     * True once we looked up function
     */
    @CompilationFinal
    private boolean initialized;

    /**
     * Assign by ref behavior
     */
    @Child
    private AssignSemanticNode assignByRefNode;

    /**
     * Assign by value behavior
     */
    @Child
    private AssignSemanticNode assignByValueNode;

    /**
     * True if function returns by ref
     */
    @CompilationFinal
    private boolean functionResultByRef;

    @CompilationFinal
    private PhpFunction phpFunction;

    /**
     * True if assignments assigns by ref
     */
    private final boolean assignmentByRef;

    private final BranchProfile programLogicError = BranchProfile.create();

    public FunctionAssignmentBehaviorNode(boolean isAssignmentByReference,
                                          PhpFunctionLookupNode lookupNode,
                                          PhpExprNode sourceNode) {

        this.assignmentByRef = isAssignmentByReference;
        this.sourceNode = sourceNode;
        this.lookupFunctionNode = lookupNode;
        this.assignByRefNode = AssignByReferenceNode.createWithoutChild();
        this.assignByValueNode = AssignByValueNode.createWithoutChild();
    }

    private void initializeNode(VirtualFrame frame) {
        PhpFunction fun = getFunction(frame);
        CompilerDirectives.transferToInterpreterAndInvalidate();

        // change compilation final fields
        this.functionResultByRef = fun.isReturnReference();
        this.phpFunction = fun;
        this.initialized = true;
    }

    private boolean copyByReference() {
        return functionResultByRef && assignmentByRef;
    }

    private boolean copyByValue() {
        return functionResultByRef && !assignmentByRef;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (!this.initialized) {
            initializeNode(frame);
        }
        Object val = sourceNode.executeGeneric(frame);

        // XXX: control flow only depends on final fields during runtime.
        // no branch prediction needed

        if (copyByReference()) {
            return assignByRefNode.executeSource(val);
        } else if (copyByValue()) {
            // XXX: Function returns by reference but we dont assign by reference
            // in this case we are forced to make a copy, this unlikely to happen
            warnForcedCopyByValue();
            return assignByValueNode.executeSource(val);
        } else {
            // XXX: We pass result directly and neither copy by reference nor value
            // in this case result is already a copy
            return val;
        }
    }

    @TruffleBoundary
    private void warnForcedCopyByValue() {
        String msg = "Function: " +
                this.phpFunction.getName() +
                " is defined to return by-reference but assignment uses by-value semantics.";
        PhpLanguage.getCurrentContext().getOutput().println(msg);
    }

    private PhpFunction getFunction(VirtualFrame f) {
        try {
            return lookupFunctionNode.executePhpFunction(f);
        } catch (UnexpectedResultException e) {
            programLogicError.enter();
            throw new PhpException("Illegal, PhpFunctionNode must return PhpFunction object", this);
        }
    }

    @Override
    public String toString() {
        return "FunctionAssignmentBehaviorNode{" +
                "sourceNode=" + sourceNode +
                ", lookupFunctionNode=" + lookupFunctionNode +
                '}';
    }
}
