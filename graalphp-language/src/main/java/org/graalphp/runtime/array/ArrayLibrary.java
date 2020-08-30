package org.graalphp.runtime.array;

import com.oracle.truffle.api.library.GenerateLibrary;
import com.oracle.truffle.api.library.GenerateLibrary.DefaultExport;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryFactory;

/**
 * Truffle libraries enable polymorphic dispatch with support for profiling/ specializations.
 * We model arrays with different backends with libraries and change backends behind the
 * scene if initial assumptions about backend change. Arrays in php are map like.
 * In many cases they are array like, however.
 * <p>
 * This Library defines the interface for backends to implement.
 * <p>
 * Important! If we add a new interface method here, truffle currently throws generic null pointer
 * exception if not all default exports implement the new method. There is currently no more
 * meaningful compile error.
 * We Better implement default behavior and throw exception for newly added methods in here.
 *
 * @author abertschi
 */
@GenerateLibrary
@DefaultExport(LongArrayLibrary.class)
@DefaultExport(ObjectArrayLibrary.class)
public abstract class ArrayLibrary extends Library {

    public static final String SPECIALIZATION_LIMIT = "2";

    // TODO for spec compliance;
    //  - Implement Map like backend for string keys, and irregular indices (i.e. -1)
    //  - capacity may be larger than currently set. PHP throws an error if an uninitialized
    //    entry is accessed
    //    - with primitive implementations this cannot easily be achieved. We could keep track of
    //      written indices with bitmap
    //    - for Object, never written <=> java null

    private static final LibraryFactory<ArrayLibrary> FACTORY =
            LibraryFactory.resolve(ArrayLibrary.class);

    public static LibraryFactory<ArrayLibrary> getFactory() {
        return FACTORY;
    }

    public static ArrayLibrary getUncached() {
        return FACTORY.getUncached();
    }

    // Semantic messages

    public boolean isArray(Object receiver) {
        return false;
    }

    /**
     * read from receiver at index index
     **/
    public Object read(Object receiver, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * write to receiver at index index
     **/
    public void write(Object receiver, int index, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * get current capacity
     **/
    public int capacity(Object receiver) {
        throw new UnsupportedOperationException();
    }

    // Implementation specific messages

    /**
     * returns true if receiver supports given value
     **/
    public boolean acceptsValue(Object receiver, Object value) {
        throw new UnsupportedOperationException();
    }

    /**
     * allocator to create new receiver
     **/
    public ArrayAllocator getArrayAllocator(Object receiver) {
        throw new UnsupportedOperationException();
    }

    /**
     * upgrade a receiver to store more generalized types, for example. long[] -> Object[]
     **/
    public ArrayAllocator generalizeForValue(Object receiver, Object newValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a shallow copy of receiver and write it into target
     * A shallow copy here is as follows;
     * if receiver contains an array of an array, we simply copy the most outer array.
     * This means the target shares the nested array.
     *
     * @see #copyDeepContents(Object, Object, int)
     */
    public void copyContents(Object receiver, Object target, int length) {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a deep copy by copy arrays of arrays.
     */
    public void copyDeepContents(Object receiver, Object target, int length) {
        throw new UnsupportedOperationException();
    }

    /**
     * grow receiver to new size, create a copy
     **/
    public Object grow(Object receiver, int newSize) {
        throw new UnsupportedOperationException();
    }

    public static ArrayAllocator getArrayCreatorForValue(Object obj) {
        if (obj instanceof Long) {
            return LongArrayAllocator.INSTANCE;
        } else {
            return ObjectArrayAllocator.INSTANCE;
        }
    }

    /**
     * to string
     **/
    public String arrayToString(Object receiver) {
        throw new UnsupportedOperationException();
    }

}