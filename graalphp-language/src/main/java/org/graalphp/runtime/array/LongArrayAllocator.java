package org.graalphp.runtime.array;

/**
 * @author abertschi
 */
public class LongArrayAllocator implements ArrayAllocator {

    public static final LongArrayAllocator ALLOCATOR = new LongArrayAllocator();

    private LongArrayAllocator() {}

    @Override
    public Object allocate(int capacity) {
        return new long[capacity];
    }

    @Override
    public boolean accepts(Object value) {
        return value instanceof Long;
    }
}
