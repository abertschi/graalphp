package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.literal.PhpStringNode;
import org.graalphp.nodes.unary.ConvertToLongNode;

/**
 * @author Avinash D'Silva <avinash.roshan.dsilva@gmail.com>
 */
@NodeInfo(shortName = ".")
public abstract class PhpConcatNode extends PhpBinaryNode {

    @Specialization
    public String doOpString(VirtualFrame f, String a, String b) {
        return a + b;
    }

    @Specialization
    public String doOpStringLong(VirtualFrame f, String a, Long b) {
        return a + b;
    }

    @Specialization
    public String doOpStringLong(VirtualFrame f, Long a, String b) {
        return a + b;
    }

    @Specialization
    public String doOpStringDouble(VirtualFrame f, String a, Double b) {
        return a + b;
    }

    @Specialization
    public String doOpStringDouble(VirtualFrame f, Double a, String b) {
        return a + b;
    }

    @Specialization
    public String doOpStringDouble(VirtualFrame f, String a, Boolean b) {
        return a + (b ? "1":"");
    }

    @Specialization
    public String doOpStringDouble(VirtualFrame f, Boolean a, String b) {
        return (a ? "1":"") + b;
    }

    @Override
    public String toString() {
        return "PhpConcatNode{" + toStringChildren() + "}";
    }
}
