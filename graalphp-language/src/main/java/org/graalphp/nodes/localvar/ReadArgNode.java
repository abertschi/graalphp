package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;

/**
 * Node which returns argument from truffle frame
 *
 * @author abertschi
 */
public class ReadArgNode extends PhpExprNode {

    private int index;

    public ReadArgNode(int index) {
        this.index = index;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            // TODO: we throw exception in strict mode
            throw new UnsupportedOperationException("invalid argument count given");
        }
    }
}
