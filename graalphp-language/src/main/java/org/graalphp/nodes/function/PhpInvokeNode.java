package org.graalphp.nodes.function;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.assign.AssignByReferenceNode;
import org.graalphp.nodes.assign.AssignByValueNode;
import org.graalphp.nodes.assign.AssignSemanticNode;
import org.graalphp.types.PhpFunction;

/**
 * Models a invocation of a non polymorphic function in php
 *
 * @author abertschi
 */
public final class PhpInvokeNode extends PhpExprNode {

    @Child
    protected PhpExprNode functionNode;

    @Children
    protected PhpExprNode[] argNodes;

    @Child
    @CompilationFinal
    protected DirectCallNode callNode;

    // Forward node dictates semantics of forwarding values; either by-ref or by-value
    @Child
    protected AssignSemanticNode forwardValueNode;

    public PhpInvokeNode(PhpExprNode[] arguments, PhpExprNode functionNode) {
        this.argNodes = arguments;
        this.functionNode = functionNode;
        this.callNode = null;
    }

    @ExplodeLoop
    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (this.callNode == null) {
            PhpFunction fun = getFunction(frame);
            CompilerDirectives.transferToInterpreterAndInvalidate();
            this.callNode = DirectCallNode.create(fun.getCallTarget());
            createForwardingNode(fun.isReturnReference());
        }

        // for a single node, number of arguments is constant
        CompilerAsserts.partialEvaluationConstant(argNodes.length);

        Object[] argVals = new Object[argNodes.length];
        for (int i = 0; i < argNodes.length; i++) {
            argVals[i] = argNodes[i].executeGeneric(frame);
        }
        return forwardValueNode.executeSource(this.callNode.call(argVals));
    }

    private void createForwardingNode(boolean ref) {
        if (ref) {
            this.forwardValueNode = insert(AssignByReferenceNode.createWithoutChild());
        } else {
            this.forwardValueNode = insert(AssignByValueNode.createWithoutChild());
        }
    }

    private PhpFunction getFunction(VirtualFrame f) {
        try {
            return functionNode.executePhpFunction(f);
        } catch (UnexpectedResultException e) {
            throw new PhpException("Illegal, PhpFunctionNode must return PhpFunction object", this);
        }
    }
}
