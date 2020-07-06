package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.PhpRuntime;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */
@NodeChild(value = "receiver")
@NodeChild(value = "index")
@NodeChild(value = "value")
public abstract class ArrayWriteNode extends PhpExprNode {

    public static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;
    private static final int DEFAULT_CAPACITY_INCREASE = PhpRuntime.INITIAL_ARRAY_CAPACITY;

    // TODO: the smallest unit for integer numbers are long, java cant have long array length.
    // we currently cast to int. for better error handling add error handling if not fit within int.
    // library.write(array.getBackend(), (int) index, value);

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
        library.write(array.getBackend(), (int) index, value);
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

        array.setBackend(library.generalizeForValue(array.getBackend(), value)
                .allocate(array.getCapacity()));
        newLibrary.write(array.getBackend(), (int) index, value);
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

        int newCap = array.getCapacity() + DEFAULT_CAPACITY_INCREASE;
        array.setBackend(library.grow(array.getBackend(),
                newCap));
        array.setCapacity(newCap);
        library.write(array.getBackend(), (int) index, value);
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
        final int oldLength = array.getCapacity();
        final int newLength = array.getCapacity() + DEFAULT_CAPACITY_INCREASE;
        final Object newBackend = library.generalizeForValue(array.getBackend(), value)
                .allocate(newLength);
        final Object oldBackend = array.getBackend();

        System.err.println(oldBackend);
        System.err.println(newBackend);
//        System.err.println(library);
//        System.err.println(newLibrary);

        library.copyContents(oldBackend, newBackend, oldLength);
        newLibrary.write(newBackend, (int) index, value);
        array.setBackend(newBackend);
        return array;
    }

    protected static boolean isArrayInBounds(PhpArray array, long index) {
        return index >= 0 && index < array.getCapacity();
    }

    protected static boolean canAppendByOne(PhpArray array, long index) {
        return array.getCapacity() == index;
    }
}
