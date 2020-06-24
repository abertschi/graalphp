package org.graalphp.nodes.binary.logical;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.binary.PhpBinaryNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = ">=")
public abstract class PhpGeNode extends PhpBinaryNode {

    @Specialization
    public boolean doOpLong(VirtualFrame f, long a, long b) {
        return a >= b;
    }

    @Specialization
    public boolean doOpDouble(VirtualFrame f, double a, double b) {
        return a >= b;
    }

    @Specialization
    protected Object fallback(Object left, Object right) {
        throw new UnsupportedOperationException("Generic greater equals is not yet supported");
    }
}