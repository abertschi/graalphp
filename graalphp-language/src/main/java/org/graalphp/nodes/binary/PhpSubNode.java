package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "-")
public abstract class PhpSubNode extends PhpBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long sub(long left, long right) {
        return Math.subtractExact(left, right);
    }

    @Specialization
    protected double sub(double left, double right) {
        return left - right;
    }

    // slow track
    @Specialization()
    protected Object sub(Object left, Object right) {
        throw new UnsupportedOperationException("we dont support sub. of other types than long yet");
    }

    @Override
    public String toString() {
        return "PhpSubNode{" + toStringChildren() + "}";

    }
}

