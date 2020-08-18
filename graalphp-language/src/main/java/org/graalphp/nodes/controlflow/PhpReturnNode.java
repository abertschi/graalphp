package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.types.PhpNull;

/**
 * Control flow Node to model return values
 *
 * @author abertschi
 */
@NodeInfo(shortName = "return")
public final class PhpReturnNode extends PhpStmtNode {

    @Child
    private PhpExprNode exec;

    public PhpReturnNode(PhpExprNode exec) {
        this.exec = exec;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        Object returnVal = null;
        if (exec != null) {
            returnVal = exec.executeGeneric(frame);
        } else {
            returnVal = PhpNull.SINGLETON;
        }
        throw new PhpReturnException(returnVal);
    }
}
