package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "<<")
public abstract class PhpLeftShiftNode extends PhpBinaryNode {

    @Specialization
    protected long doOpLong(VirtualFrame f, long a, long b) {
        return a << b;
    }

    @Specialization
    protected Object fallback(Object left, Object right) {
        throw new UnsupportedOperationException("Generic shift operator is not yet supported");
    }
}
