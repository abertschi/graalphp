package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "*")
public abstract class PhpMulNode extends PhpBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long multiply(long left, long right) {
        return Math.multiplyExact(left, right);
    }

    @Specialization
    protected double multiply(double left, double right) {
        return left * right;
    }

    @Specialization
    protected Object multiply(Object left, Object right) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "PhpMulNode{" + toStringChildren() + "}";

    }
}

