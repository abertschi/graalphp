package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.controlflow.execption.PhpReturnException;
import org.graalphp.types.PhpNull;

import java.util.Collections;
import java.util.List;

/**
 * @author abertschi
 */
@NodeInfo(language = PhpLanguage.ID, description = "entry point to execute source code")
public class PhpGlobalScopeNode extends RootNode {

    @Child
    private PhpStmtNode body;

    public PhpGlobalScopeNode(PhpLanguage language,
                              FrameDescriptor globalDescriptor,
                              List<PhpStmtNode> body,
                              boolean returnLastExpr) {
        super(language, globalDescriptor);
        prepareBody(body, returnLastExpr);
    }

    // XXX: for testing, if returnLastExpr = true,
    // always return last expression from script if it is an expression
    private void prepareBody(List<PhpStmtNode> body, boolean returnLastExpr) {
        if (returnLastExpr){
            if (body.size() > 0){
                final PhpStmtNode lastStmt = body.get(body.size() - 1);
                if (lastStmt instanceof PhpExprNode) {
                    body.add(new PhpReturnNode((PhpExprNode) lastStmt));
                }
            }
        }
        this.body = new PhpStmtListNode(body.toArray(new PhpStmtNode[body.size()]));
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO: integrate arguments

        Object result = PhpNull.SINGLETON;
        try {
            body.executeVoid(frame);
        } catch (PhpReturnException e) {
            result = e.getReturnValue();
        }
        return result;
    }
}
