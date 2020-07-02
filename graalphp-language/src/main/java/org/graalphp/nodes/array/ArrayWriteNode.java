package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */
@NodeChild(value = "receiver")
@NodeChild(value = "index")
@NodeChild(value = "value")
public abstract class ArrayWriteNode extends PhpExprNode {

    public static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;
    private static final int DEFAULT_CAPACITY_INCREASE = 10;

    @Specialization(
            guards = {"library.acceptsValue(array.getBackend(), value)"},
            limit = LIMIT)
    protected PhpArray writeLongSameType(
            PhpArray array,
            long index,
            long value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library) {

        library.write(array.getBackend(), (int) index, value);
        return array;
    }
}
