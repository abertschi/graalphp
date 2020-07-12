package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.unary.ConvertToLongNode;

/**
 * Left Shift operator
 * <p>
 * https://github.com/php/php-langspec/blob/master/spec/10-expressions.md#bitwise-shift-operators
 *
 * @author abertschi
 */
@NodeInfo(shortName = "<<")
public abstract class PhpShiftLeftNode extends PhpBinaryNode {

    private final BranchProfile exceptionReached = BranchProfile.create();

    public static PhpShiftLeftNode createAndConvertToLong(PhpExprNode e1, PhpExprNode e2) {
        return PhpShiftLeftNodeGen.create(
                ConvertToLongNode.createAndWrap(e1),
                ConvertToLongNode.createAndWrap(e2)
        );
    }

    @Specialization
    protected long doOpLong(VirtualFrame f, long num, long count) {
        // num << count
        if (count < 0) {
            exceptionReached.enter();
            throw new PhpException("Shift count cannot be negative", this);
        }
        // Left shifts where the shift count is greater than the bit width of the integer type
        // (e.g. 32 or 64) must always result in 0, even if there is no native processor support
        // for this.
        if (count > 64) {
            return 0;
        }
        return num << count;
    }

    @Specialization
    protected Object fallback(Object left, Object right) {
        throw new UnsupportedOperationException("Generic shift operator is not yet supported");
    }

    @Override
    public String toString() {
        return "PhpLeftShiftNode{}";
    }
}
