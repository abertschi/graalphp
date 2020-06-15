package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.BlockNode;
import org.graalphp.nodes.PhpStmtNode;

/**
 * Represents a sequence of stmts in PHP.
 *
 * @author abertschi
 */
public class PhpStmtListNode extends PhpStmtNode
        implements BlockNode.ElementExecutor<PhpStmtNode> {

    /*
     * XXX: we use truffle BlockNode API which gives truffle more room for improvement
     * when executing multiple stmts.
     */
    @Child
    private BlockNode<PhpStmtNode> block;

    public PhpStmtListNode(PhpStmtNode[] stmts) {
        this.block = (stmts != null && stmts.length > 0)
                ? BlockNode.create(stmts, this) : null;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        if (block != null) {
            block.executeVoid(frame, BlockNode.NO_ARGUMENT);
        }
    }

    @Override
    public void executeVoid(VirtualFrame frame, PhpStmtNode node, int index, int argument) {
        node.executeVoid(frame);
    }
}
