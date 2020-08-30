package org.graalphp.runtime.array;

/**
 * Receiver allocation for Object[] arrays
 *
 * @author abertschi
 */
public class ObjectArrayAllocator implements ArrayAllocator {

    public static final ObjectArrayAllocator INSTANCE = new ObjectArrayAllocator();

    private ObjectArrayAllocator() {
    }

    @Override
    public Object createArray(int capacity) {
        return new Object[capacity];
    }

    @Override
    public boolean acceptsValue(Object value) {
        return true;
    }
}

