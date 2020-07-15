package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.BlockNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a sequence of stmts.
 *
 * @author abertschi
 */
public final class StmtListNode extends PhpStmtNode
        implements BlockNode.ElementExecutor<PhpStmtNode> {

    /*
     * XXX: we use truffle BlockNode API which gives truffle more room for improvement
     * when executing multiple stmts.
     */
    @Child
    private BlockNode<PhpStmtNode> block;

    public StmtListNode(PhpStmtNode[] stmts) {
        this.block = (stmts != null && stmts.length > 0)
                ? BlockNode.create(stmts, this) : null;
    }

    public StmtListNode(List<PhpStmtNode> stmts) {
        this(stmts.toArray(new PhpStmtNode[stmts.size()]));
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

    public List<PhpStmtNode> getStatements() {
        if (block == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(block.getElements()));
    }

    @Override
    @TruffleBoundary
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getClass().getSimpleName()).append(": ");
        for (PhpStmtNode s : getStatements()) {
            buf.append(s.toString());
            buf.append(";");
        }
        return buf.toString();

    }
}
