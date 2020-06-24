package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

/**
 * @author abertschi
 */
public class PhpDoWhileNode extends PhpStmtNode {

    @Child
    PhpWhileNode whileNode;

    public PhpDoWhileNode(PhpStmtNode body, PhpExprNode condition) {
        this.whileNode= new PhpWhileNode(condition, body);
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        whileNode.executeVoid(frame);
    }
}
