package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.array.ArrayCopyByValueNodeGen;

/**
 * Proxy for ReadArgNode which copies Arrays by value
 *
 * @author abertschi
 */
public final class ReadArgCopyByValueNode extends PhpExprNode {

    @Child
    protected PhpExprNode node;

    public ReadArgCopyByValueNode(int index) {
        this.node = ArrayCopyByValueNodeGen.create(new ReadArgNode(index));
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return node.executeGeneric(frame);
    }
}
