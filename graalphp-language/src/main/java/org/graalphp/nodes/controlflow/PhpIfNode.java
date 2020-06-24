package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNode;

/**
 * @author abertschi
 */
public class PhpIfNode extends PhpStmtNode {

    @Child
    private PhpConvertToBooleanNode conditionNode;

    @Child
    private PhpStmtNode ifNode;

    @Child
    // XXX: may be null if no else branch
    private PhpStmtNode elseNode;

    public PhpIfNode(PhpExprNode condition,
                     PhpStmtNode ifBranch,
                     PhpStmtNode elseBranch) {
        this.conditionNode = PhpConvertToBooleanNode.createAndWrap(condition);
        this.ifNode = ifBranch;
        this.elseNode = elseBranch;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        // TODO: add branch profiling

        if (evaluateCondition(frame)) {
            ifNode.executeVoid(frame);
        } else if (elseNode != null) {
            elseNode.executeVoid(frame);
        }
    }

    private boolean evaluateCondition(VirtualFrame frame) {
        try {
            return conditionNode.executeBoolean(frame);
        } catch (UnexpectedResultException e) {
            throw new PhpException("Type Error, expected boolean in condition, got: " + e.getResult(),
                    conditionNode);
        }
    }

    @Override
    public String toString() {
        return "PhpIfNode{" +
                "condition=" + conditionNode +
                ", ifBranch=" + ifNode +
                ", elseBranch=" + elseNode +
                '}';
    }
}
