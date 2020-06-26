package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "+")
public abstract class PhpAddNode extends PhpBinaryNode {

    @Specialization(rewriteOn = ArithmeticException.class)
    protected long add(long left, long right) {
        return Math.addExact(left, right);
    }

    @Specialization
    protected double add(double left, double right) {
        return left + right;
    }

    @Override
    public String toString() {
        return "PhpAddNode{" + toStringChildren() + "}";
    }
}
