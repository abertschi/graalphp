package org.graalphp.nodes.assign;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.function.PhpFunctionLookupNode;
import org.graalphp.types.PhpFunction;

/**
 * We typically have a forwarding node in front of invoke nodes.
 * Forwarding nodes copy values according to specified semantics. Either by value or by reference.
 * If we return by value, we may copy the return value twice if we assign a function result to a
 * variable.
 * This node skips copy by value if the function returns by value already.
 * Function needs to be looked up as it may not be available yet once we reach assignment.
 *
 * @author abertschi
 * @see AssignSemanticNode
 */
public final class FunctionAssignmentBehaviorNode extends PhpExprNode {

    @Child
    private PhpExprNode sourceNode;

    @Child
    private PhpFunctionLookupNode lookupFunctionNode;

    @Child
    private AssignSemanticNode assignByRefNode;

    @CompilationFinal
    private boolean isCopyByReference;

    @CompilationFinal
    private boolean isInitialized = false;

    private BranchProfile programLogicError = BranchProfile.create();

    public FunctionAssignmentBehaviorNode(PhpFunctionLookupNode lookupNode,
                                          PhpExprNode sourceNode) {
        this.sourceNode = sourceNode;
        this.lookupFunctionNode = lookupNode;
        this.assignByRefNode = AssignByReferenceNodeGen.createWithoutChild();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (!this.isInitialized) {
            PhpFunction fun = getFunction(frame);
            CompilerDirectives.transferToInterpreterAndInvalidate();
            isCopyByReference = fun.isReturnReference();
            this.isInitialized = true; // change compilation fine fields
            // if function returns by value the invoke node already makes a copy of return value
            // in this case we dont need to create another copy as we already have a copy
        }
        Object val = sourceNode.executeGeneric(frame);
        if (!isCopyByReference) {
            return val;
        } else {
            return assignByRefNode.executeSource(val);
        }
    }

    private PhpFunction getFunction(VirtualFrame f) {
        try {
            return lookupFunctionNode.executePhpFunction(f);
        } catch (UnexpectedResultException e) {
            programLogicError.enter();
            throw new PhpException("Illegal, PhpFunctionNode must return PhpFunction object", this);
        }
    }
}
