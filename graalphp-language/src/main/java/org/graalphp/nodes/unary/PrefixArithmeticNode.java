package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;

/**
 * Prefix increment/ decrement node
 * <p>
 * ... = ++$a <=> ($a = $a + 1; return $a)
 * <p>
 * <p>
 * This node could be built with assign and increment nodes.
 * For simplicity we create a new node which model behavior explicitly.
 *
 * @author abertschi
 */
@NodeInfo(shortName = "++/--$a")
@NodeField(name = "slot", type = FrameSlot.class)
@NodeField(name = "operator", type = int.class)
public abstract class PrefixArithmeticNode extends PhpExprNode {

    protected abstract FrameSlot getSlot();

    protected abstract int getOperator();

    @Specialization(guards = "f.isBoolean(getSlot())")
    protected long readBool(VirtualFrame f) {
        long val = FrameUtil.getBooleanSafe(f, getSlot()) ? 1 : 0;
        val = val + getOperator();
        f.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Long); // slow path
        f.setLong(getSlot(), val);
        return val;
    }

    @Specialization(guards = "f.isLong(getSlot())")
    protected long readLong(VirtualFrame f) {
        long val = FrameUtil.getLongSafe(f, getSlot());
        val = val + getOperator();
        f.setLong(getSlot(), val);
        return val;
    }

    @Specialization(guards = "f.isDouble(getSlot())")
    protected double readDouble(VirtualFrame f) {
        double val = FrameUtil.getDoubleSafe(f, getSlot());
        val = val + getOperator();
        f.setDouble(getSlot(), val);
        return val;
    }

    @Specialization
    protected Object readGeneric(VirtualFrame frame) {
        throw new PhpException("cannot increment/decrement non numerical value", this);
    }
}
