package org.graalphp.nodes.array;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.PhpArray;

/**
 * Creates a new long based array node, which generalizes if needed.
 *
 * @author abertschi
 */
public final class NewArrayNode extends PhpExprNode {

    @Override
    public PhpArray executeGeneric(VirtualFrame frame) {
        return ArrayFactory.newArray();
    }
}
