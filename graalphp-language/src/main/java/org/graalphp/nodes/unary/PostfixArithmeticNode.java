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
 * postfix node for increment/ decrement
 * <p>
 * ... = $a++ <=> ($a1 = $a; $a = $a + 1; return $a1)
 *
 * @author abertschi
 */
@NodeInfo(shortName = "$a++/--")
@NodeField(name = "slot", type = FrameSlot.class)
@NodeField(name = "operator", type = int.class)
public abstract class PostfixArithmeticNode extends PhpExprNode {

    protected abstract FrameSlot getSlot();

    protected abstract int getOperator();

    @Specialization(guards = "f.isBoolean(getSlot())")
    protected long readBool(VirtualFrame f) {
        long val = FrameUtil.getBooleanSafe(f, getSlot()) ? 1 : 0;
        f.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Long); // slow path
        f.setLong(getSlot(), val + getOperator());
        return val;
    }

    // we convert to double if overflow
    @Specialization(guards = "f.isLong(getSlot())", rewriteOn = ArithmeticException.class)
    protected long readLong(VirtualFrame f) {
        long val = FrameUtil.getLongSafe(f, getSlot());
        long newVal = Math.addExact(val, getOperator());
        f.setLong(getSlot(), newVal);
        return val;
    }

    @Specialization(guards = "f.isLong(getSlot())")
    protected double readLongOverUnderflow(VirtualFrame f) {
        double val = FrameUtil.getLongSafe(f, getSlot());
        f.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Double);
        f.setDouble(getSlot(), val + getOperator());
        return val;
    }

    @Specialization(guards = "f.isDouble(getSlot())")
    protected double readDouble(VirtualFrame f) {
        double val = FrameUtil.getDoubleSafe(f, getSlot());
        f.setDouble(getSlot(), val + getOperator());
        return val;
    }

    @Specialization
    protected Object readGeneric(VirtualFrame frame) {
        throw new PhpException("cannot increment/decrement non numerical value", this);
    }
}
