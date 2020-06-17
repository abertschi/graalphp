package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;

/**
 * Truffle idiomatic way to write a (local) variable onto the stack (Truffle frame)
 *
 * @author abertschi
 */
@NodeChild(value = "srcNode", type = PhpExprNode.class) // value to be assigned
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class WriteVarNode extends PhpExprNode {

    // storage slot in frame
    protected abstract FrameSlot getSlot();

    @Specialization
    // TODO: make this specialization for primitive types to avoid boxing
    protected Object write(VirtualFrame frame, Object value) {
//        frame.getFrameDescriptor().setFrameSlotKind(getSlot(), FrameSlotKind.Object);
        frame.setObject(getSlot(), value);
        return value;
    }
}
