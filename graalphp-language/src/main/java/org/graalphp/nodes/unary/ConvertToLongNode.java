package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;

/**
 * Convert to long number
 *
 * @author abertschi
 */
public abstract class ConvertToLongNode extends PhpUnaryNode {

    public static ConvertToLongNode createAndWrap(PhpExprNode n) {
        if (n instanceof ConvertToLongNode) {
            return (ConvertToLongNode) n;
        } else {
            return ConvertToLongNodeGen.create(n);
        }
    }

    @Specialization
    protected long doBoolean(boolean val) {
        return val ? 1 : 0;
    }

    @Specialization
    protected long doLong(long val) {
        return val;
    }

    @Specialization
    protected long doDouble(double val) {
        return (long) val;
    }

    @Override
    public String toString() {
        return "ConvertToLongNode{" + getValueNode() + "}";
    }
}
