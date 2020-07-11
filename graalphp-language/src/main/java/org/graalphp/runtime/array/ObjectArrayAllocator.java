package org.graalphp.runtime.array;

/**
 * Receiver allocation for Object[] arrays
 *
 * @author abertschi
 */
public class ObjectArrayAllocator implements ArrayAllocator {

    public static final ObjectArrayAllocator ALLOCATOR = new ObjectArrayAllocator();

    private ObjectArrayAllocator() {
    }

    @Override
    public Object allocate(int capacity) {
        return new Object[capacity];
    }

    @Override
    public boolean accepts(Object value) {
        return true;
    }
}

