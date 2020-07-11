package org.graalphp.nodes.array;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;

import java.util.List;

/**
 * Execute a list of expressions and return result as Object[]
 *
 * @author abertschi
 */
public final class ExecuteValuesNode extends PhpExprNode {

    @Children
    private PhpExprNode[] nodes;

    public ExecuteValuesNode(PhpExprNode[] nodes) {
        this.nodes = nodes;
    }

    public ExecuteValuesNode(List<PhpExprNode> nodes) {
        this.nodes = nodes.toArray(new PhpExprNode[nodes.size()]);
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
