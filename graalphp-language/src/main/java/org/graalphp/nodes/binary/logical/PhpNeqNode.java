package org.graalphp.nodes.binary.logical;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.binary.PhpBinaryNode;
import org.graalphp.runtime.array.PhpArray;

/**
 * @see PhpEqNode
 * @author abertschi
 */
@NodeInfo(shortName = "!=")
public abstract class PhpNeqNode extends PhpBinaryNode {

    @Specialization
    public boolean doOpLong(VirtualFrame f, long a, long b) {
        return a != b;
    }

    @Specialization
    public boolean doOpDouble(VirtualFrame f, double a, double b) {
        return a != b;
    }

    @Specialization
    public boolean doOpArray(VirtualFrame f, PhpArray a, PhpArray b) {
        throw new UnsupportedOperationException("Array Equality not implemented");
    }

    @Specialization()
    protected Object fallback(Object left, Object right) {
        return left != right;
    }
}