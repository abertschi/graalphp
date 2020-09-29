package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * Represents floats in graalphp
 * <p>
 * Must support at least the range and precision of IEEE 754 64-bit double-precision representation.
 * Hence is based on double in java
 *
 * @author abertschi
 */
@NodeInfo(shortName = "string")
public final class PhpStringNode extends PhpExprNode {

    private final String val;

    public PhpStringNode(String v) {
        this.val = v;
    }

    @Override
    public boolean executeBoolean(VirtualFrame f) {
        if(val.equals("")){
            return false;
        }else if(val.equals("false")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpStringNode{" +
                "val=" + val +
                '}';
    }
}
