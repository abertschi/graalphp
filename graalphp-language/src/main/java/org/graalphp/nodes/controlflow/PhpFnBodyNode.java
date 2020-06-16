package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.controlflow.execption.PhpReturnException;
import org.graalphp.types.PhpNull;

/**
 * Node to represent a construct in PHP which can return a value
 * (function, or return from global scope)
 *
 * @author abertschi
 */
public class PhpFnBodyNode extends PhpExprNode {

    // TODO: add branch profiling

    @Child
    private PhpStmtNode body;

    public PhpFnBodyNode(PhpStmtNode body) {
        this.body = body;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        try {
            body.executeVoid(frame);
        } catch(PhpReturnException e){
            return e.getReturnValue();
        }
        return PhpNull.SINGLETON;
    }
}