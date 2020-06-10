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
        long res = left / right;
        // TODO: when does this overflow, investigate

        return res;
    }

    @Specialization()
    protected Object div(Object left, Object right) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "PhpDivNode{}";
    }
}

