package org.graalphp.runtime.array;

/**
 * Allocator behavior for arrays
 *
 * @author abertschi
 */
public interface ArrayAllocator {

    /**
     * Allocate array of given capacity
     **/
    Object createArray(int capacity);

    /**
     * can store value?
     **/
    boolean acceptsValue(Object value);
}
