package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNode;

/**
 * Truffle idiomatic way to represent a while node
 *
 * @author abertschi
 */
public final class PhpWhileRepeatingNode extends Node implements RepeatingNode {

    private final BranchProfile continueTaken = BranchProfile.create();
    private final BranchProfile breakTaken = BranchProfile.create();

    @Child
    private PhpConvertToBooleanNode conditionNode;

    @Child
    private PhpStmtNode bodyNode;

    public PhpWhileRepeatingNode(PhpConvertToBooleanNode conditionNode,
                                 PhpStmtNode bodyNode) {
        this.conditionNode = conditionNode;
        this.bodyNode = bodyNode;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {
        if (!evaluateCondition(frame)) {
            // XXX: do we need profiling here or is it already applied by truffle?
            // exit loop
            return false;
        }
        try {
            bodyNode.executeVoid(frame);
            return true;
        } catch (PhpBreakException e) {
            breakTaken.enter();
            return false;
        } catch (PhpContinueException e) {
            continueTaken.enter();
            return true;
        }
    }

    private boolean evaluateCondition(VirtualFrame frame) {
        try {
            return conditionNode.executeBoolean(frame);
        } catch (UnexpectedResultException e) {
            // XXX: this cannot occur as we have a node which converts result to boolean
            throw new PhpException("Illegal state, conditional node does not evaluate to boolean:" +
                    conditionNode.toString(), conditionNode);
        }
    }
}
