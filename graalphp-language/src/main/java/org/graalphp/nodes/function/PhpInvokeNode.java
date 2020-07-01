package org.graalphp.nodes.function;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.types.PhpFunction;

/**
 * Models a invocation of a non polymorphic function in php
 *
 * @author abertschi
 */
public final class PhpInvokeNode extends PhpExprNode {

    @Child
    protected PhpExprNode function;

    @Children
    protected PhpExprNode[] argNodes;

    @Child
    @CompilationFinal
    private DirectCallNode callNode;

//    @Child private InteropLibrary library;

    // TODO Change this to an abstract nodes, introduce object [] types
    public PhpInvokeNode(PhpExprNode[] arguments, PhpExprNode function) {
        this.argNodes = arguments;
        this.function = function;
        this.callNode = null;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (this.callNode == null){
            PhpFunction o = (PhpFunction) function.executeGeneric(frame);
            CompilerDirectives.transferToInterpreterAndInvalidate();
            this.callNode = DirectCallNode.create(o.getCallTarget());
        }

        // for a single node, number of arguments is constant
        CompilerAsserts.partialEvaluationConstant(argNodes.length);

        Object[] argVals = new Object[argNodes.length];
        for (int i = 0; i < argNodes.length; i++) {
            argVals[i] = argNodes[i].executeGeneric(frame);
        }
        // TODO: do polymorphic inline cache technique here
        return this.callNode.call(argVals);
    }
}
