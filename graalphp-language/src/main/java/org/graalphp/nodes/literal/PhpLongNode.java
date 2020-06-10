package org.graalphp.nodes.literal;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.nodes.PhpExprNode;

/**
 * A long literal
 *
 * @author abertschi
 */
@NodeInfo(shortName = "long")
public class PhpLongNode extends PhpExprNode {

    private final long val;

    public static PhpLongNode parse(String val) throws NumberFormatException {
        // TODO: handle big integer
        // TODO: handle binary, hex representations, octal
        long v = Long.parseLong(val);
        return new PhpLongNode(v);
    }

    public PhpLongNode(long val) {
        this.val = val;
    }

    @Override
    public long executeLong(VirtualFrame f) throws UnexpectedResultException {
        return val;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return val;
    }

    @Override
    public String toString() {
        return "PhpLongNode{" +
                "val=" + val +
                '}';
    }
}
