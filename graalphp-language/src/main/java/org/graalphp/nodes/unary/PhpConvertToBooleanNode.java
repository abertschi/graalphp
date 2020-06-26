package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;

/**
 * In PHP, a value used in a condition is automatically converted to boolean
 * This node exploits truffles node specialization and converts a supported value to boolean type.
 *
 * @author abertschi
 */
public abstract class PhpConvertToBooleanNode extends PhpUnaryNode {

    public static PhpConvertToBooleanNode createAndWrap(PhpExprNode n) {
        if (n instanceof PhpConvertToBooleanNode) {
            return (PhpConvertToBooleanNode) n;
        } else {
            return PhpConvertToBooleanNodeGen.create(n);
        }
    }

    @Specialization
    protected boolean doBoolean(boolean val) {
        return val;
    }

    @Specialization
    protected boolean doLong(long val) {
        return val != 0;
    }

    @Specialization
    protected boolean doDouble(double val) {
        return val != 0.0;
    }

    @Override
    public String toString() {
        return "PhpConvertToBooleanNode{" + getValueNode() + "}";
    }

}
