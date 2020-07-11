package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.unary.ConvertToLongNode;

/**
 * Right Shift operator
 * <p>
 * https://github.com/php/php-langspec/blob/master/spec/10-expressions.md#bitwise-shift-operators
 *
 * @author abertschi
 */
@NodeInfo(shortName = ">>")
public abstract class PhpRightShiftNode extends PhpBinaryNode {

    private final BranchProfile exceptionProfile = BranchProfile.create();
    private final BranchProfile shiftTooLargeProfile = BranchProfile.create();

    public static PhpRightShiftNode create(PhpExprNode n) {
        return PhpRightShiftNodeGen.create(ConvertToLongNode.createAndWrap(n));
    }

    @Specialization
    protected long doOpLong(VirtualFrame f, long e1, long e2) {
        // e1 >> e2
        if (e2 < 0) {
            exceptionProfile.enter();
            throw new PhpException("Shift e2 cannot be negative", this);
        }
        // Right shifts where the shift e2 is greater than the bit
        // width of the integer type (e.g. 32 or 64) must always
        // result in 0 when e1 is positive and -1 when e1 is negative,
        // even if there is no native processor support for this.
        if (e2 > 64) {
            shiftTooLargeProfile.enter();
            return e1 >= 0 ? 1 : -1;
        }
        return e1 >>> e2;
    }

    @Specialization
    protected Object fallback(Object left, Object right) {
        throw new UnsupportedOperationException("Generic shift operator is not yet supported");
    }

    @Override
    public String toString() {
        return "PhpRightShiftNode{}";
    }
}
