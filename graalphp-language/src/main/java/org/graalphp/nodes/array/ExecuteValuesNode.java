package org.graalphp.nodes.array;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
public final class ExecuteValuesNode extends PhpExprNode {

    @Children
    private final PhpExprNode[] nodes;

    public ExecuteValuesNode(PhpExprNode[] nodes) {
        this.nodes = nodes;
    }

    @ExplodeLoop
    @Override
    public Object[] executeGeneric(VirtualFrame frame) {
        CompilerAsserts.partialEvaluationConstant(nodes.length);

        // XXX: we may not have arguments, array will have zero len
        Object[] array = new Object[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            array[i] = nodes[i].executeGeneric(frame);
        }
        return array;
    }
}
