package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.PhpArray;

import java.util.List;

/**
 * Create new array with initial values
 *
 * @author abertschi
 * @see NewArrayNode
 */
@NodeChild(value = "initialValues", type = ExecuteValuesNode.class)
public abstract class NewArrayInitialValuesNode extends PhpExprNode {

    public static NewArrayInitialValuesNode create(List<PhpExprNode> nodes) {
        return NewArrayInitialValuesNodeGen.create(new ExecuteValuesNode(nodes));
    }

    @ExplodeLoop
    @Specialization
    protected PhpArray createNew(Object[] values,
                                 @Cached ArrayWriteNode writeNode) {
        if (values != null) {
            PhpArray array = ArrayFactory.newArray(values.length);
            for (int i = 0; i < values.length; i++) {
                writeNode.executeWrite(array, i, values[i]);
            }
            return array;
        }
        return ArrayFactory.newArray();
    }
}
