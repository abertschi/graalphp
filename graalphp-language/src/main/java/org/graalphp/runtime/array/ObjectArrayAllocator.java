package org.graalphp.runtime.array;

/**
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

