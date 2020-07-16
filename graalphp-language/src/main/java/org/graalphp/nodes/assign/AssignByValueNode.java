package org.graalphp.nodes.assign;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.ArrayAllocator;
import org.graalphp.runtime.array.ArrayFactory;
import org.graalphp.runtime.array.ArrayLibrary;
import org.graalphp.runtime.array.PhpArray;

/**
 * Node which forwards all values but arrays and copy arrays before forwarding.
 * This implements PHP default assign by value semantics
 *
 * @author abertschi
 */
@NodeChild(value = "source", type = PhpExprNode.class)
public abstract class AssignByValueNode extends PhpExprNode {

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
    protected Object copyArray(
            PhpArray array,
            @CachedLibrary(limit = ArrayLibrary.SPECIALIZATION_LIMIT) ArrayLibrary lib) {

        ArrayAllocator allocator = lib.allocator(array.getBackend());
        Object newBackend = allocator.allocate(array.getCapacity());
        lib.copyDeepContents(array.getBackend(), newBackend, array.getCapacity());
        return ArrayFactory.newArray(newBackend, array.getCapacity());
    }

    @Specialization(guards = "notArray(val)")
    protected Object forwardObject(Object val) {
        // Objects are copied by reference (their reference is copied)
        return val;
    }

    protected final boolean notArray(Object o) {
        return !(o instanceof PhpArray);
    }

    protected abstract PhpExprNode getSource();

    @Override
    public String toString() {
        return "AssignByValueNode{" + getSource() + "}";
    }
}
