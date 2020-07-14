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
 * @author abertschi
 */
@NodeChild(value = "backend")
@NodeChild(value = "index")
public abstract class ArrayReadNode extends PhpExprNode {

    private static final String LIMIT = ArrayLibrary.SPECIALIZATION_LIMIT;

    protected abstract Node getBackend();

    protected abstract Node getIndex();

    // XXX: We currently support no Map like fallback containers if array capacity is exceeded
    @Specialization(limit = LIMIT,
            rewriteOn = ArrayCapacityExceededException.class)
    Object doLookup(PhpArray array,
                    long index,
                    @CachedLibrary("array.getBackend()") ArrayLibrary arrays) {

        return arrays.read(array.getBackend(), convertToInt(index));
    }

    private int convertToInt(long val) {
        if (val < Integer.MAX_VALUE && val > Integer.MIN_VALUE) {
            return (int) val;
        } else {
            throw new ArrayCapacityExceededException("array index is too large for java arrays",
                    this);
        }
    }

    @Override
    public String toString() {
        return "ArrayReadNode{backend: " + getBackend() + "; index; " + getIndex() + "}";
    }
}
