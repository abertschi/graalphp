package org.graalphp.nodes.function;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;
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

    // TODO Change this to an abstract nodes, introduce object [] types
    public PhpInvokeNode(PhpExprNode[] arguments, PhpExprNode function) {
        this.argNodes = arguments;
        this.function = function;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        // TODO: improve this, avoid casting
        PhpFunction fn = (PhpFunction) function.executeGeneric(frame);

        // for a single node, number of arguments is constant
        CompilerAsserts.partialEvaluationConstant(argNodes.length);

        Object[] argVals = new Object[argNodes.length];
        for (int i = 0; i < argNodes.length; i++) {
            argVals[i] = argNodes[i].executeGeneric(frame);
        }
        // TODO: do polymorphic inline cache technique here
        return fn.getCallTarget().call(argVals);
    }
}
