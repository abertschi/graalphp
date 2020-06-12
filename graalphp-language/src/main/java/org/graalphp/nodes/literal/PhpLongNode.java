package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.nodes.PhpExprNode;

/**
 * Represents integer numbers in graalphp
 *
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
