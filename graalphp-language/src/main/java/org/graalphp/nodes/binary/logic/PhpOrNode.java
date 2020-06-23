package org.graalphp.nodes.binary.logic;

import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "||")
public final class PhpOrNode extends PhpShortCircuitNode {

    public PhpOrNode(PhpExprNode left, PhpExprNode right) {
        super(left, right);
    }

    @Override
    protected boolean shouldEvaluateRight(boolean left) {
        return true;
    }

    @Override
    protected boolean executeBinaryOp(boolean left, boolean right) {
        return left || right;
    }
}

