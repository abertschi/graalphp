package org.graalphp.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.controlflow.PhpReturnException;
import org.graalphp.nodes.controlflow.PhpReturnNode;
import org.graalphp.types.PhpNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abertschi
 */
@NodeInfo(language = PhpLanguage.ID, description = "entry point to execute source code")
public class PhpGlobalRootNode extends RootNode {

    @Child
    private PhpStmtNode body;

    public PhpGlobalRootNode(PhpLanguage language,
                             FrameDescriptor globalDescriptor,
                             List<PhpStmtNode> body,
                             boolean returnLastExpr) {
        super(language, globalDescriptor);
        prepareBody(body, returnLastExpr);
    }

    // XXX: for testing, if returnLastExpr = true,
    // always return last expression from script if it is an expression
    // this simplifies testing when there is no support for print yet
    private void prepareBody(List<PhpStmtNode> body, boolean returnLastExpr) {
        if (returnLastExpr) {
            ArrayList<PhpStmtNode> stmts = new ArrayList<>(body);
            if (stmts.size() > 0) {
                final PhpStmtNode lastStmt = body.get(body.size() - 1);
                if (lastStmt instanceof PhpExprNode) {
                    stmts.remove(body.size() - 1);
                    stmts.add(new PhpReturnNode((PhpExprNode) lastStmt));
                }
            }
            this.body = new StmtListNode(stmts.toArray(new PhpStmtNode[stmts.size()]));
        } else {
            this.body = new StmtListNode(body.toArray(new PhpStmtNode[body.size()]));
        }
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO: integrate arguments for argv, argc
        Object result = PhpNull.SINGLETON;
        try {
            body.executeVoid(frame);
        } catch (PhpReturnException e) {
            result = e.getReturnValue();
        }
        return result;
    }
}
