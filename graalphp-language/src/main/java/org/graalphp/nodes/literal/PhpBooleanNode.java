package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "bool")
public final class PhpBooleanNode extends PhpExprNode {

    private final boolean val;

    public PhpBooleanNode(boolean val) {
        this.val = val;
    }

    @Override
    public boolean executeBoolean(VirtualFrame f) {
        return val;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpBooleanNode{" +
                "val=" + val +
                '}';
    }
}
