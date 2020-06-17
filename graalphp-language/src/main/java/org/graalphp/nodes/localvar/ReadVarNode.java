package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;

/**
 * Node to read a local variable from the current frame.
 *
 * @author abertschi
 */
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class ReadVarNode extends PhpExprNode {

    // location in frame where to read from
    protected abstract FrameSlot getSlot();

    // TODO: add more specializations to avoid boxing
    @Specialization
    protected Object read(VirtualFrame frame) {
        return frame.getValue(getSlot());
    }
}
