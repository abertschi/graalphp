package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * Represents integer numbers in graalphp
 * <p>
 * Based on a primitive long in java
 *
 * @author abertschi
 */
@NodeInfo(shortName = "long")
public class PhpLongNode extends PhpExprNode {

    public static final long PHP_INT_SIZE = Long.SIZE;
    public static final long PHP_INT_MIN = Long.MIN_VALUE;
    public static final long PHP_INT_MAX = Long.MAX_VALUE;

    private final long val;

    public PhpLongNode(long val) {
        this.val = val;
    }

    @Override
    public long executeLong(VirtualFrame f) {
        return val;
    }

    @Override
    public boolean executeBoolean(VirtualFrame f) {
        return val != 0;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpLongNode{" +
                "val=" + val +
                '}';
    }
}
