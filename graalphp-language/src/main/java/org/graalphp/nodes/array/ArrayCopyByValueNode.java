package org.graalphp.nodes.array;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayAllocator;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * Node which forwards all values but arrays
 * and copy arrays before forwarding.
 * This fulfills PHPs copy by value behavior for arrays.
 *
 * @author abertschi
 */
@NodeChild(value = "source", type = PhpExprNode.class)
public abstract class ArrayCopyByValueNode extends PhpExprNode {

    private static final Logger LOG =
            PhpLogger.getLogger(ArrayCopyByValueNode.class.getSimpleName());

    public static ArrayCopyByValueNode create(PhpExprNode source) {
        return  ArrayCopyByValueNodeGen.create(source);
    }

    @Specialization
    protected boolean forwardBool(boolean val) {
        return val;
    }

    @Specialization
    protected long forwardLong(long val) {
        return val;
    }

    @Specialization
    protected double forwardDouble(double val) {
        return val;
    }

    @Specialization
    protected Object copyArray(PhpArray array,
                               @CachedLibrary(limit =
                                       ArrayLibrary.SPECIALIZATION_LIMIT)
                                       ArrayLibrary lib) {

//        LOG.info("Write by value: " + lib.arrayToString(array.getBackend()));

        ArrayAllocator allocator = lib.allocator(array.getBackend());
        Object newBackend = allocator.allocate(array.getCapacity());
        lib.copyContents(array.getBackend(), newBackend, array.getCapacity());
        PhpArray newArray = ArrayFactory.newArray(newBackend, array.getCapacity());
        return newArray;
    }

    @Specialization(guards = "notArray(val)")
    protected Object forwardObject(Object val) {
        return val;
    }

    protected final boolean notArray(Object o) {
        return !(o instanceof PhpArray);
    }

}
