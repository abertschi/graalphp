package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameUtil;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * Node to read a local variable from the current frame.
 *
 * @author abertschi
 */
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class ReadLocalVarNode extends PhpExprNode {

    private static final Logger L = PhpLogger.getLogger(ReadLocalVarNode.class.getSimpleName());

    protected abstract FrameSlot getSlot();

    @Specialization(guards = "f.isBoolean(getSlot())")
    protected boolean readBool(VirtualFrame f) {
        return FrameUtil.getBooleanSafe(f, getSlot());
    }

    @Specialization(guards = "f.isLong(getSlot())")
    protected long readLong(VirtualFrame f) {
        return FrameUtil.getLongSafe(f, getSlot()); // utility method, catches exception
    }

    @Specialization(guards = "f.isDouble(getSlot())")
    protected double readDouble(VirtualFrame f) {
        return FrameUtil.getDoubleSafe(f, getSlot());
    }

    @Specialization(replaces = {"readLong", "readBool", "readDouble"})
    protected Object readGeneric(VirtualFrame frame) {
        if (!frame.isObject(getSlot())) {
            // XXX: slot in frame is not yet set to object
            // but specialization reached most general case
            // we have to set it manually
            CompilerDirectives.transferToInterpreter();
            Object result = frame.getValue(getSlot());
            frame.setObject(getSlot(), result);
//            L.info("readGeneric in ReadLocalVarNode: " + result);
            return result;
        }

        Object objectSafe = FrameUtil.getObjectSafe(frame, getSlot());
//        L.info("readGeneric in ReadLocalVarNode: " + objectSafe);
        return objectSafe;

    }
}
