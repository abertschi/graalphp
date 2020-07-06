package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.PhpRuntime;
import org.graalphp.runtime.array.LongArrayAllocator;
import org.graalphp.runtime.array.PhpArray;

/**
 * Creates a new long based array node, which generalizes if needed.
 *
 * @author abertschi
 */
public abstract class NewArrayNode extends PhpExprNode {

//    @Child
//    ArrayWriteNode initialValueWrite;

    @Specialization
    protected Object createNew() {
        final int capacity = PhpRuntime.INITIAL_ARRAY_CAPACITY;
        return new PhpArray(LongArrayAllocator.ALLOCATOR.allocate(capacity), capacity);
    }
}
