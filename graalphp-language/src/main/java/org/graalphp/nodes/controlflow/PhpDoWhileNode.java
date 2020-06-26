package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "do-while")
public final class PhpDoWhileNode extends PhpStmtNode {

    @Child
    private PhpStmtNode body;

    @Child
    private PhpWhileNode whileNode;

    private final BranchProfile continueTaken = BranchProfile.create();
    private final BranchProfile breakTaken = BranchProfile.create();

    public PhpDoWhileNode(PhpStmtNode body, PhpExprNode condition) {
        // XXX: can we store the same instance in two child fields?
        // or do we have to perform a deep copy?
        // Alternatively we can model the first iteration within the body and add additional
        // an additional condition, evaluate with benchmark

        this.body = body;
        this.whileNode = new PhpWhileNode(condition, body);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            body.executeVoid(frame);
        } catch (PhpBreakException e) {
            breakTaken.enter();
            return;
        } catch (PhpContinueException e) {
            continueTaken.enter();
            // continue
        }
        whileNode.executeVoid(frame);
    }
}
