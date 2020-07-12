package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * Pos unary node. Does not do anything. for completeness it is added.
 *
 * @author abertschi
 */
@NodeInfo(shortName = "+")
public abstract class PhpPosNode extends PhpUnaryNode {

    @Specialization()
    protected long pos(long val) {
        return val;
    }

    @Specialization()
    protected double pos(double val) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpPosNode{" + getValueNode() + "}";
    }
}
