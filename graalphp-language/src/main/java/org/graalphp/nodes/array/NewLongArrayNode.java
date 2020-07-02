package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.LongArrayAllocator;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */

@NodeChild(value = "values", type = ExecuteValuesNode.class)
public abstract class NewLongArrayNode extends PhpExprNode {

    @ExplodeLoop
    @Specialization
    protected Object createNew(Object[] vals) {
        return new PhpArray(LongArrayAllocator.ALLOCATOR.allocate(10), 10);
//
//        if (vals.length > 0) {
//            int capacity = vals.length; // TODO, better defaults
//
//            Object backend = ArrayLibrary.getAllocatorForValue(vals[0]).allocate(capacity);
//            final PhpArray phpArray = new PhpArray(backend, capacity);
//
//            return phpArray;
//        } else {
//
//        }
    }
}
