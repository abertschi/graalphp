package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayLibrary;

/**
 * @author abertschi
 */
@NodeChild(value = "backend")
@NodeChild(value = "index")
public abstract class ArrayReadNode extends PhpExprNode {

    // TODO update LIMIT based on backends
    public static final String LIMIT = "2";

    @Specialization(guards = "arrays.isArray(array)", limit = LIMIT)
    Object doLookup(Object array, long index,
                    @CachedLibrary("array") ArrayLibrary arrays) {

        // TODO: cast, we only support long types, but java cant have long array capacities
        return arrays.read(array, (int) index);
    }
}
