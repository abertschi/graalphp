package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.ConditionProfile;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNode;

/**
 * If/ Else expression of form A ? B : C;
 *
 * @author abertschi
 */
public final class PhpIfInlineNode extends PhpExprNode {

    @Child
    private PhpConvertToBooleanNode conditionNode;

    @Child
    private PhpExprNode ifNode;

    @Child
    private PhpExprNode elseNode;

    private final ConditionProfile condition = ConditionProfile.createCountingProfile();

    public PhpIfInlineNode(PhpExprNode condition,
                           PhpExprNode ifNode,
                           PhpExprNode elseNode) {
        this.conditionNode = PhpConvertToBooleanNode.createAndWrap(condition);
        this.ifNode = ifNode;
        this.elseNode = elseNode;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (condition.profile(evaluateCondition(frame))) {
            return ifNode.executeGeneric(frame);
        } else {
            return elseNode.executeGeneric(frame);
        }
    }

    private boolean evaluateCondition(VirtualFrame f) {
        try {
            return conditionNode.executeBoolean(f);
        } catch (UnexpectedResultException e) {
            throw new PhpException("condition must always resolve in boolean", this);
        }
    }
}
