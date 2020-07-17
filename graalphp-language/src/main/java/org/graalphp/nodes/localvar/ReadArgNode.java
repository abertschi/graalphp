package org.graalphp.nodes.localvar;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.profiles.BranchProfile;
import org.graalphp.nodes.PhpExprNode;

/**
 * Node which returns argument from truffle frame
 *
 * @author abertschi
 * @see org.graalphp.runtime.assign.AssignRuntimeFactory for wrappers to assign by reference/ value
 */
public final class ReadArgNode extends PhpExprNode {

    private final int index;

    public ReadArgNode(int index) {
        this.index = index;
    }

    private final BranchProfile invalidArgumentProfile = BranchProfile.create();

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            invalidArgumentProfile.enter();
            throw new UnsupportedOperationException("invalid argument count given");
        }
    }
}
