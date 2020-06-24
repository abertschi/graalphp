package org.graalphp.nodes.binary.logical;

import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "&&")
public final class PhpAndNode extends PhpShortCircuitNode {

    public PhpAndNode(PhpExprNode left, PhpExprNode right) {
        super(left, right);
    }

    @Override
    protected boolean shouldEvaluateRight(boolean left) {
        return left;
    }

    @Override
    public String toString() {
        return "PhpAndNode{" + toStringChildren() + "}";
    }

    @Override
    protected boolean executeBinaryOp(boolean left, boolean right) {
        return left && right;
    }
}

