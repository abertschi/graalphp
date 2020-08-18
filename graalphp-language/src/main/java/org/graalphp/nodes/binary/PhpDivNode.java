package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "/")
public abstract class PhpDivNode extends PhpBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long div(long left, long right) {
        // XXX: can this be done with guards and removed from this specialization?
        if (right == 0) {
            throw new RuntimeException("div by 0");
        }
        long res = left / right;
        if (left == Long.MIN_VALUE && right == -1) {
            throw new NumberFormatException("long division overflow");
        }
        return res;
    }

    @Specialization
    protected double div(double left, double right) {
        return left / right;
    }

    @Override
    public String toString() {
        return "PhpDivNode{" + toStringChildren() + "}";
    }
}

