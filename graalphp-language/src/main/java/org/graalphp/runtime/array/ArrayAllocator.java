package org.graalphp.runtime.array;

/**
 * @author abertschi
 */
public interface ArrayAllocator {

    Object allocate(int capacity);

    boolean accepts(Object value);
}
