package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.literal.PhpStringNode;
import org.graalphp.nodes.unary.ConvertToLongNode;

/**
 * @author abertschi
 */
@NodeInfo(shortName = ".")
public abstract class PhpConcatNode extends PhpBinaryNode {

    @Specialization
    public String doOpString(VirtualFrame f, String a, String b) {
        return a + b;
    }


    @Override
    public String toString() {
        return "PhpConcatNode{" + toStringChildren() + "}";
    }
}
