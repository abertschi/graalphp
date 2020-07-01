package org.graalphp.nodes.function;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.nodes.controlflow.PhpReturnException;
import org.graalphp.types.PhpNull;

/**
 * Node to represent a construct in PHP which can return a value
 * (function, or return from global scope)
 *
 * Uses Truffle idiomatic way to wrap return type into Control Flow exception.
 *
 * @author abertschi
 */
public final class PhpReturnBodyNode extends PhpExprNode {

    private final BranchProfile continueTaken = BranchProfile.create();

    @Child
    private PhpStmtNode body;

    public PhpReturnBodyNode(PhpStmtNode body) {
        this.body = body;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        try {
            body.executeVoid(frame);
        } catch(PhpReturnException e){
            continueTaken.enter();
            return e.getReturnValue();
        }
        return PhpNull.SINGLETON;
    }
}
