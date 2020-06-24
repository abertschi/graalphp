package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpStmtNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "break")
public final class PhpBreakNode extends PhpStmtNode {

    // TODO: PHP supports break with an expression,
    // we currently do not support this

    @Override
    public void executeVoid(VirtualFrame frame) {
        throw new PhpBreakException();
    }
}
