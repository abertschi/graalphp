package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 * <p>
 * returns array instead of value
 * @see ArrayWriteNode
 */
@NodeChild(value = "array")
@NodeChild(value = "index")
@NodeChild(value = "value")
public abstract class ArrayWriteReturnArrayNode extends PhpExprNode {

    @Specialization
    protected PhpArray eval(PhpArray array,
                            long index,
                            Object value,
                            @Cached ArrayWriteNode write) {
        write.executeWrite(array, index, value);
        return array;
    }
}
