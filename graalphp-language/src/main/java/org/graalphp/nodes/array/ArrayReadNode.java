package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.exception.ArrayCapacityExceededException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */
@NodeChild(value = "backend")
@NodeChild(value = "index")
public abstract class ArrayReadNode extends PhpExprNode {

    private static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;

    // XXX: We currently support no Map like fallback containers if array capacity is exceeded
    @Specialization(limit = LIMIT,
            rewriteOn = ArrayCapacityExceededException.class)
    Object doLookup(PhpArray array,
                    long index,
                    @CachedLibrary("array.getBackend()") ArrayLibrary arrays) {

        return arrays.read(array.getBackend(), convertToInt(index));
    }

    private int convertToInt(long val) {
        try {
            return Math.toIntExact(val);
        } catch (ArithmeticException e) {
            throw new ArrayCapacityExceededException("array index is too large for java arrays"
                    , this);
        }
    }
}
