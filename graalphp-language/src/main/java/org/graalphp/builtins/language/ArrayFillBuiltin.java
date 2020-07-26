package org.graalphp.builtins.language;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.builtins.PhpBuiltinNode;
import org.graalphp.exception.ArrayCapacityExceededException;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.LongArrayAllocator;
import org.graalphp.runtime.array.ObjectArrayAllocator;
import org.graalphp.runtime.array.PhpArray;

/**
 * Implementation of the array_fill builtin in PHP
 * array_fill ( int $start_index , int $num , mixed $value ) : array
 * <p>
 * https://www.php.net/manual/en/function.array-fill.php
 *
 * @author abertschi
 */
@NodeInfo(shortName = ArrayFillBuiltin.NAME)
public abstract class ArrayFillBuiltin extends PhpBuiltinNode {

    public static final String NAME = "array_fill";

    @Specialization
    protected PhpArray arrayFillLong(long startIndex, long num, long val,
                                     @CachedLibrary(limit = "1") ArrayLibrary libs) {
        int arraySize = toInt(num);
        Object backend = LongArrayAllocator.ALLOCATOR.allocate(arraySize);
        PhpArray phpArray = ArrayFactory.newArray(backend, arraySize);

        for (int i = 0; i < arraySize; i++) {
            libs.write(backend, i, val);
        }
        return phpArray;
    }

    @Specialization
    protected PhpArray arrayFillDouble(long startIndex, long num, double val,
                                       @CachedLibrary(limit = "1") ArrayLibrary libs) {
        int arraySize = toInt(num);
        Object backend = ArrayLibrary.getAllocatorForValue(val);
        PhpArray phpArray = ArrayFactory.newArray(backend, arraySize);

        for (int i = 0; i < arraySize; i++) {
            libs.write(backend, i, val);
        }
        return phpArray;
    }

    @Specialization
    protected PhpArray arrayFillObject(long startIndex, long num, Object val,
                                       @CachedLibrary(limit = "1") ArrayLibrary libs) {
        int arraySize = toInt(num);
        Object backend = ObjectArrayAllocator.ALLOCATOR.allocate(arraySize);
        PhpArray phpArray = ArrayFactory.newArray(backend, arraySize);

        for (int i = 0; i < arraySize; i++) {
            libs.write(backend, i, val);
        }
        return phpArray;
    }

    private int toInt(long val) {
        try {
            return Math.toIntExact(val);
        } catch (ArithmeticException e) {
            throw new ArrayCapacityExceededException(
                    "Array size must be of long domain, got: " + val, this);
        }
    }
}
