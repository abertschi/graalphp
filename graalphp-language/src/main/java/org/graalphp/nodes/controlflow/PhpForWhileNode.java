package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.StmtListNode;
import org.graalphp.nodes.unary.PhpConvertToBooleanNodeGen;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abertschi
 */
@NodeInfo(shortName = "for")
public class PhpForWhileNode extends PhpStmtNode {

    @Child
    PhpStmtNode initNode;

    @Child
    PhpWhileNode whileNode;

    public PhpForWhileNode(List<PhpExprNode> inits,
                           List<PhpExprNode> conditions,
                           List<PhpExprNode> updaters,
                           List<PhpStmtNode> loopBody) {

        this.initNode = new ExprGroupNode(inits);
        PhpExprNode condition = PhpConvertToBooleanNodeGen.create(new ExprGroupNode(conditions));
        PhpStmtNode updater = new ExprGroupNode(updaters);

        LinkedList<PhpStmtNode> bodyStmts = new LinkedList<>(loopBody);
        bodyStmts.add(updater);

        StmtListNode stmtList = new StmtListNode(bodyStmts);
        this.whileNode = new PhpWhileNode(condition, stmtList);
    }

    public void executeVoid(VirtualFrame frame) {
        initNode.executeVoid(frame);
        whileNode.executeVoid(frame);
    }
}
