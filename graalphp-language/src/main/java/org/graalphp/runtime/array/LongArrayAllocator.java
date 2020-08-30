package org.graalphp.runtime.array;

/**
 * Allocation strategy for long[].
 *
 * @author abertschi
 */
public class LongArrayAllocator implements ArrayAllocator {

    public static final LongArrayAllocator INSTANCE = new LongArrayAllocator();

    private LongArrayAllocator() {}

    @Override
    public Object createArray(int capacity) {
        return new long[capacity];
    }

    @Override
    public boolean acceptsValue(Object value) {
        return value instanceof Long;
    }
}
