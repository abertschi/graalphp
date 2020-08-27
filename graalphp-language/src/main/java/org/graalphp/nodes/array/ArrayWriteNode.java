package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.Node;
import org.graalphp.exception.ArrayCapacityExceededException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;

/**
 * Array write node uses variable array backends and generalizes if
 * needed
 * <p>
 * Semantically, a array write expression returns the value to write
 * not the array.
 * <p>
 * $a = array(1, 2, 3);
 * $b = $a[0] = 1;
 * print($b); // 1
 *
 * @author abertschi
 */
@NodeChild(value = "receiver")
@NodeChild(value = "index")
@NodeChild(value = "value")
public abstract class ArrayWriteNode extends PhpExprNode {

    public static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;
    private static final int INCREASE_FACTOR = 2;

    public static ArrayWriteNode create() {
        return ArrayWriteNodeGen.create(null, null, null);
    }

    public abstract Object executeWrite(PhpArray array, long index, Object value);

    /**
     * write within bounds of same type
     */
    @Specialization(
            guards = {
                    "library.acceptsValue(array.getBackend(), value)"
                    , "isArrayInBounds(array, index)"
            },
            limit = LIMIT)
    protected Object writeInBoundsSameType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library) {
        library.write(array.getBackend(), convertToInt(index), value);
        return value;
    }

    /**
     * write within existing capacity of different type => needs generalization
     */
    @Specialization(
            guards = {
                    "!library.acceptsValue(array.getBackend(), value)"
                    , "isArrayInBounds(array, index)"
            },
            limit = LIMIT)
    protected Object writeInBoundsWrongType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library,
            @CachedLibrary(limit = LIMIT) ArrayLibrary libraryNewBackend) {

        final int newLength = getIncreasedCapacity(array, index);
        final Object oldBackend = array.getBackend();
        final Object newBackend = library
                .generalizeForValue(array.getBackend(), value).allocate(newLength);
        library.copyContents(oldBackend, newBackend, array.getCapacity());
        array.setBackend(newBackend);
        array.setCapacity(newLength);

        libraryNewBackend.write(array.getBackend(), convertToInt(index), value);
        return value;
    }

    /**
     * Capacity is too little, we grow array, same type
     * <p>
     * XXX: This may be inefficient if index is too large
     * Ideally we distinguish between to more cases once we implement map data structures
     *   1. do we insert at the end of the array (index: backend.capacity)
     *   2. do we insert somewhere else -> convert to map structure
     */
    @Specialization(
            guards = {
                    "library.acceptsValue(array.getBackend(), value)"
                    , "isOutOfBounds(array, index)"
            },
            limit = LIMIT)
    protected Object outOfBoundsSameType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library,
            @CachedLibrary(limit = LIMIT) ArrayLibrary libraryNewBackend) {
        int newLength = getIncreasedCapacity(array, index);
        array.setBackend(library.grow(array.getBackend(), newLength));
        array.setCapacity(newLength);

        libraryNewBackend.write(array.getBackend(), convertToInt(index), value);
        return value;
    }

    /**
     * Capacity is too little, we grow array, different type
     *
     * XXX: This may be inefficient if index is too large
     */
    @Specialization(
            guards = {
                    "!library.acceptsValue(array.getBackend(), value)"
                    , "isOutOfBounds(array, index)"
            },
            limit = LIMIT)
    protected Object outOfBoundsDifferentType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library,
            @CachedLibrary(limit = LIMIT) ArrayLibrary libraryNewBackend) {

        growBackendGeneralize(array, value, getIncreasedCapacity(array, index), library);
        libraryNewBackend.write(array.getBackend(), convertToInt(index), value);
        return value;
    }

    protected static boolean isArrayInBounds(PhpArray array, long index) {
        return index >= 0 && index < array.getCapacity();
    }

    protected static boolean isOutOfBounds(PhpArray array, long index) {
        return array.getCapacity() <= index;
    }

    protected abstract Node getReceiver();

    protected abstract Node getIndex();

    protected abstract Node getValue();

    private void growBackendGeneralize(PhpArray array,
                                       Object value,
                                       int newLength,
                                       ArrayLibrary library) {
        final Object oldBackend = array.getBackend();
        final Object newBackend = library
                .generalizeForValue(array.getBackend(), value).allocate(newLength);
        library.copyContents(oldBackend, newBackend, array.getCapacity());
        array.setBackend(newBackend);
        array.setCapacity(newLength);
    }

    private int getIncreasedCapacity(PhpArray array, long index) {
        int newLength = array.getCapacity() * INCREASE_FACTOR;
        if (newLength < index) {
            newLength = convertToInt(index) * INCREASE_FACTOR;
        }
        return newLength;
    }

    private int convertToInt(long val) {
        try {
            return Math.toIntExact(val);
        } catch (ArithmeticException e) {
            throw new ArrayCapacityExceededException("array index is too large for java arrays"
                    , this);
        }
    }

    @Override
    public String toString() {
        return "ArrayWriteNode{"
                + "Array: " + getReceiver()
                + "; Index: " + getIndex()
                + "; Value: " + getValue()
                + "}";
    }
}
