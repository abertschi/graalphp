package org.graalphp.builtins.language;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.builtins.PhpBuiltinNode;
import org.graalphp.exception.ArrayCapacityExceededException;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.LongArrayAllocator;
import org.graalphp.runtime.array.PhpArray;

/**
 * Implementation of the array_fill builtin in PHP
 * array_fill ( int $start_index , int $num , mixed $value ) : array
 * <p>
 * https://www.php.net/manual/en/function.array-fill.php
 *
 * @author abertschi
 */
public abstract class ArrayFillBuiltin extends PhpBuiltinNode {

    @Specialization
    protected PhpArray arrayFillLong(long startIndex, long num, long val,
                                     @CachedLibrary(limit = "1") ArrayLibrary libs) {
        int arraySize = toInt(val);
        Object backend = LongArrayAllocator.ALLOCATOR.allocate(arraySize);
        PhpArray phpArray = ArrayFactory.newArray(backend, arraySize);
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
