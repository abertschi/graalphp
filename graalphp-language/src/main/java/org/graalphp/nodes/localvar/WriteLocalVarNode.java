package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * Truffle idiomatic way to write a variable onto the stack (Truffle frame)
 * TODO: we have to use a materialized frame when supporting global variables
 *
 * @author abertschi
 */
@NodeChild(value = "srcNode", type = PhpExprNode.class)
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class WriteLocalVarNode extends PhpExprNode {

    private static final Logger LOG = PhpLogger.getLogger(WriteLocalVarNode.class.getSimpleName());

    // storage slot in frame
    protected abstract FrameSlot getSlot();

    @Specialization(guards = "isSlotBoolOrIllegal(frame)")
    protected boolean writeBool(VirtualFrame frame, boolean val) {
        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Boolean);
        frame.setBoolean(getSlot(), val);
        return val;
    }

    @Specialization(guards = "isSlotLongOrIllegal(frame)")
    protected long writeLong(VirtualFrame frame, long value) {
        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Long);
        frame.setLong(getSlot(), value);
        return value;
    }

    @Specialization(guards = "isSlotDoubleOrIllegal(frame)")
    protected double writeDouble(VirtualFrame frame, double val) {
        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Double);
        frame.setDouble(getSlot(), val);
        return val;
    }

    // XXX: evaluate if we should keep the specializations, or use replace
    @Specialization(replaces = {"writeLong", "writeBool", "writeDouble"})
    protected Object write(VirtualFrame frame, Object value) {
        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Object);
        frame.setObject(getSlot(), value);
        return value;
    }

    protected final boolean isSlotLongOrIllegal(VirtualFrame frame) {
        final FrameDescriptor desc = frame.getFrameDescriptor();
        final FrameSlotKind kind = desc.getFrameSlotKind(getSlot());
        return kind == FrameSlotKind.Long || kind == FrameSlotKind.Illegal;
    }

    protected final boolean isSlotBoolOrIllegal(VirtualFrame frame) {
        final FrameDescriptor desc = frame.getFrameDescriptor();
        final FrameSlotKind kind = desc.getFrameSlotKind(getSlot());
        return kind == FrameSlotKind.Boolean || kind == FrameSlotKind.Illegal;
    }

    protected final boolean isSlotDoubleOrIllegal(VirtualFrame frame) {
        final FrameDescriptor desc = frame.getFrameDescriptor();
        final FrameSlotKind kind = desc.getFrameSlotKind(getSlot());
        return kind == FrameSlotKind.Double || kind == FrameSlotKind.Illegal;
    }
}
