package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
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
        // TODO: do polymorphic inline cache technique here
        return fn.getCallTarget().call(argVals);
    }

//    private PhpFunction resolveFunction(VirtualFrame virtualFrame) {
//        try {
//            return this.function.executePhpFunction(virtualFrame);
//        } catch (UnexpectedResultException e) {
//            throw new UnsupportedSpecializationException(this,
//                    new Node[] {this.functionNode}, e);
//        }
//    }
}
