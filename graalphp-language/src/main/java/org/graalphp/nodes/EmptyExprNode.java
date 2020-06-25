package org.graalphp.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.types.PhpNull;

/**
 * A dummy node which returns nothing
 * Used instead of null in nodes to prevent null checks
 *
 * @author abertschi
 */
public final class EmptyExprNode extends PhpExprNode {

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return PhpNull.SINGLETON;
    }
}
