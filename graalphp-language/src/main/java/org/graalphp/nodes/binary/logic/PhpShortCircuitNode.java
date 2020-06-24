package org.graalphp.nodes.binary.logic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNodeGen;

/**
 * @author abertschi
 */
public abstract class PhpShortCircuitNode extends PhpExprNode {

    @Child
    private PhpConvertToBooleanNode leftNode;

    @Child
    private PhpConvertToBooleanNode rightNode;

    public PhpShortCircuitNode(PhpExprNode left, PhpExprNode right) {
        this.leftNode = convertToBooleanNode(left);
        this.rightNode = convertToBooleanNode(right);
    }

    private PhpConvertToBooleanNode convertToBooleanNode(PhpExprNode n) {
        if (n instanceof PhpConvertToBooleanNode) {
            return (PhpConvertToBooleanNode) n;
        }
        return PhpConvertToBooleanNodeGen.create(n);
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return executeBoolean(frame);
    }

    @Override
    public boolean executeBoolean(VirtualFrame f) {
        boolean left;
        try {
            left = leftNode.executeBoolean(f);
        } catch (UnexpectedResultException e) {
            throw new PhpException("Type error, expected boolean for left node but got: " + e.getResult(),
                    leftNode);
        }
        boolean right;
        if (shouldEvaluateRight(left)) {
            try {
                right = rightNode.executeBoolean(f);
            } catch (UnexpectedResultException e) {
                // TODO: create type error exception
                throw new PhpException("Type error, expected boolean for right node but got: " + e.getResult(),
                        rightNode);
            }
            return executeBinaryOp(left, right);
        } else {
            return left;
        }
    }

    protected String toStringChildren() {
        return "left: " + leftNode + ", right: " + rightNode;
    }

    protected abstract boolean shouldEvaluateRight(boolean left);

    protected abstract boolean executeBinaryOp(boolean left, boolean right);
}
