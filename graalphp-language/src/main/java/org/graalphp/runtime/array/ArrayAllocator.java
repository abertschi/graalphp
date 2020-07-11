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
    Object allocate(int capacity);

    /**
     * can store value?
     **/
    boolean accepts(Object value);
}
