package org.graalphp.nodes.controlflow;


import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.controlflow.execption.PhpReturnException;
import org.graalphp.types.PhpNull;

/**
 * Controlflow Node to model return values
 *
 * @author abertschi
 */
public class PhpReturnNode extends PhpStmtNode {

    @Child private PhpExprNode exec;

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
