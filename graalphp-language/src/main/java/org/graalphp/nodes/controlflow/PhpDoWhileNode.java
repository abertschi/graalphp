package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "do-while")
public final class PhpDoWhileNode extends PhpStmtNode {

    @Child
    PhpStmtNode body;

    @Child
    PhpWhileNode whileNode;

    public PhpDoWhileNode(PhpStmtNode body, PhpExprNode condition) {
        // XXX: can we store the same instance in two child fields?
        // or do we have to perform a deep copy?
        this.body = body;
        this.whileNode = new PhpWhileNode(condition, body);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            body.executeVoid(frame);
        } catch (PhpBreakException e) {
            return;
        } catch (PhpContinueException e) {
            // continue
        }
        whileNode.executeVoid(frame);
    }
}
