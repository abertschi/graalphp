package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.exception.ArrayCapacityExceededException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.PhpRuntime;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * Array write node uses variable array backends and generalizes if needed
 *
 * @author abertschi
 */
@NodeChild(value = "receiver")
@NodeChild(value = "index")
@NodeChild(value = "value")
public abstract class ArrayWriteNode extends PhpExprNode {

    public static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;
    private static final Logger L = PhpLogger.getLogger(ArrayWriteNode.class.getSimpleName());
    private static final boolean DO_TRACE = true;
    private static final int DEFAULT_CAPACITY_INCREASE = PhpRuntime.INITIAL_ARRAY_CAPACITY;

    private static void log(String msg) {
        if (!DO_TRACE) return;
        L.info(msg);
    }

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
    protected PhpArray writeInBoundsSameType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library) {

        log("writeInBoundsSameType");

        library.write(array.getBackend(), convertToInt(index), value);
        return array;
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
    protected PhpArray writeInBoundsWrongType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library,
            @CachedLibrary(limit = LIMIT) ArrayLibrary newLibrary) {

        log("writeInBoundsWrongType");

        final int len = array.getCapacity();
        final Object oldBackend = array.getBackend();
        final Object newBackend = library
                .generalizeForValue(array.getBackend(), value).allocate(len);

        library.copyContents(oldBackend, newBackend, len);
        newLibrary.write(newBackend, convertToInt(index), value);
        array.setBackend(newBackend);

        return array;
    }

    /**
     * Capacity is too little by one, we grow array, same type
     */
    @Specialization(
            guards = {
                    "library.acceptsValue(array.getBackend(), value)"
                    , "canAppendByOne(array, index)"
            },
            limit = LIMIT)
    protected PhpArray appendByOneSameType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library) {

        log("appendByOneSameType");

        final int newLength = array.getCapacity() + DEFAULT_CAPACITY_INCREASE;
        array.setBackend(library.grow(array.getBackend(), newLength));
        array.setCapacity(newLength);

        library.write(array.getBackend(), convertToInt(index), value);
        return array;
    }

    /**
     * Capacity is too little by one, we grow array, different type
     */
    @Specialization(
            guards = {
                    "!library.acceptsValue(array.getBackend(), value)"
                    , "canAppendByOne(array, index)"
            },
            limit = LIMIT)
    protected PhpArray appendByOneDifferentType(
            PhpArray array,
            long index,
            Object value,
            @CachedLibrary("array.getBackend()") ArrayLibrary library,
            @CachedLibrary(limit = LIMIT) ArrayLibrary newLibrary) {

        log("appendByOneDifferentType");

        final int oldLength = array.getCapacity();
        final int newLength = oldLength + DEFAULT_CAPACITY_INCREASE;
        final Object oldBackend = array.getBackend();
        final Object newBackend = library
                .generalizeForValue(array.getBackend(), value).allocate(newLength);

        library.copyContents(oldBackend, newBackend, oldLength);
        newLibrary.write(newBackend, convertToInt(index), value);
        array.setBackend(newBackend);
        array.setCapacity(newLength);
        return array;
    }

    protected static boolean isArrayInBounds(PhpArray array, long index) {
        return index >= 0 && index < array.getCapacity();
    }

    protected static boolean canAppendByOne(PhpArray array, long index) {
        return array.getCapacity() == index;
    }

    private int convertToInt(long val) {
        if (val < Integer.MAX_VALUE && val > Integer.MIN_VALUE) {
            return (int) val;
        } else {
            throw new ArrayCapacityExceededException("array index is too large for java arrays",
                    this);
        }
    }
}
