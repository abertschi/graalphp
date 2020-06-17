package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import org.graalphp.types.PhpFunction;

/**
 * Models a invocation of a non polymorphic function in php
 *
 * @author abertschi
 */
public class PhpInvokeNode extends PhpExprNode {

    @Child
    protected PhpExprNode function;

    @Children
    protected PhpExprNode[] argNodes;

//    // TODO: investigate
//    @Child
//    protected DirectCallNode target;


    public PhpInvokeNode(PhpExprNode[] arguments, PhpExprNode function) {
        this.argNodes = arguments;
        this.function = function;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {

        // TODO: improve this, avoid casting
        PhpFunction fn = (PhpFunction) function.executeGeneric(frame);

        // TOOD: what if fewer arguments are passed?
        CompilerAsserts.compilationConstant(argNodes.length);

        Object[] argVals = new Object[argNodes.length];
        for (int i = 0; i < argNodes.length; i++) {
            argVals[i] = argNodes[i].executeGeneric(frame);
        }
        return fn.getCallTarget().call(argVals);
    }
}
