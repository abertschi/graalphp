package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.exception.PhpTypeError;

/**
 * In PHP, a value used in a condition is automatically converted to boolean
 * This node exploits truffles node specialization and converts a supported value to boolean type.
 *
 * @author abertschi
 */
public abstract class PhpConvertToBooleanNode extends PhpUnaryNode {

    @Specialization
    boolean doBoolean(boolean val) {
        return val;
    }

    @Specialization
    boolean doLong(long val) {
        return val != 0;
    }

    @Specialization
    boolean doDouble(double val) {
        return val != 0.0;
    }

    @Specialization
    boolean doGeneric(Object o) {
        throw new PhpTypeError("Type error, cannot convert " + o + " to boolean", this);
    }

    @Override
    public String toString() {
        return "PhpConvertToBooleanNode{" + getValueNode() + "}";
    }

}
