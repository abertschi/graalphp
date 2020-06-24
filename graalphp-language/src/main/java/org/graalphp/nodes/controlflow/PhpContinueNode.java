package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpStmtNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "continue")
public final class PhpContinueNode extends PhpStmtNode {

    // TODO: PHP supports continue with an expression,
    // we currently do not support this

    @Override
    public void executeVoid(VirtualFrame frame) {
        throw new PhpContinueException();
    }
}
