package org.graalphp.nodes.assign;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.Node;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.runtime.array.PhpArray;

/**
 * Node which assigns a value by reference instead of default behavior which is by value
 * This represents the & operator for assignments
 * <p>
 * We currently implement a subset, namely assign for Arrays
 * <p>
 * Ideas to implement assign by reference for other types;
 * - Wrap integer within BoxedInteger and pass reference around
 *
 * @author abertschi
 */
@NodeChild(value = "source")
public abstract class AssignByReferenceNode extends PhpExprNode {

    protected abstract Node getSource();

    public abstract Object executeAssign(Object source);

    @Specialization
    protected Object forwardArray(
            PhpArray array) {
        // XXX: We currently support assign by reference of Arrays
        return array;
    }

    @Specialization(guards = "notArray(val)")
    protected Object forwardObject(Object val) {
        throw new PhpException("Assign by reference not yet supported for "
                + val.getClass().getSimpleName(), this);
    }

    protected final boolean notArray(Object o) {
        return !(o instanceof PhpArray);
    }

    @Override
    public String toString() {
        return "AssignByReferenceNode{" + getSource() + "}";
    }

}
