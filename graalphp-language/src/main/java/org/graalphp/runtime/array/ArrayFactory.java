package org.graalphp.runtime.array;

import org.graalphp.runtime.PhpRuntime;

/**
 * @author abertschi
 */
public final class ArrayFactory {

    public static PhpArray newArray() {
        final int capacity = PhpRuntime.INITIAL_ARRAY_CAPACITY;
        PhpArray array = new PhpArray(LongArrayAllocator.ALLOCATOR.allocate(capacity), capacity);
        return array;
    }
}
