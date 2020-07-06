package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
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

    // TODO: the smallest unit for integer numbers are long, java cant have long array length.
    // we currently cast to int. for better error handling add error handling if not fit within int.
    // return arrays.read(array.getBackend(), (int) index);

    @Specialization(guards = "arrays.isArray(array.getBackend())", limit = LIMIT)
    Object doLookup(PhpArray array,
                    long index,
                    @CachedLibrary("array.getBackend()") ArrayLibrary arrays) {

        return arrays.read(array.getBackend(), (int) index);
    }
}
