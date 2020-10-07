package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.nodes.PhpExprNode;

/**
 * Convert to string
 *
 * @author Avinash D'Silva <avinash.roshan.dsilva@gmail.com>
 */
public abstract class ConvertToStringNode extends PhpUnaryNode {

    public static ConvertToStringNode createAndWrap(PhpExprNode n) {
        if (n instanceof ConvertToStringNode) {
            return (ConvertToStringNode) n;
        } else {
            return ConvertToStringNodeGen.create(n);
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
    protected long doDouble(double val){
        return (long) val;
    }

    @Override
    public String toString() {
        return "ConvertToStringNode{" + getValueNode() + "}";
    }
}