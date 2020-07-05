package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.LongArrayAllocator;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */

public abstract class NewLongArrayNode extends PhpExprNode {

    //    @ExplodeLoop
    @Specialization
    protected Object createNew() {
        return new PhpArray(LongArrayAllocator.ALLOCATOR.allocate(30), 30);
    }
}
