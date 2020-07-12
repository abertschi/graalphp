package org.graalphp.nodes.unary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * Logical not operator
 * @author abertschi
 */
@NodeInfo(shortName = "!")
public abstract class PhpNotNode extends PhpUnaryNode {

    public static PhpNotNode createAndConvertToBoolean(PhpExprNode n) {
        return PhpNotNodeGen.create(PhpConvertToBooleanNode.createAndWrap(n));
    }

    @Specialization
    public boolean doNot(boolean b) {
        return !b;
    }
}
