package org.graalphp.nodes.binary.logic;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.exception.PhpException;
import org.graalphp.nodes.binary.PhpBinaryNode;

/**
 * @author abertschi
 */
// TODO: make this a short circuit
@NodeInfo(shortName = "||")
public abstract class PhpOrNode extends PhpBinaryNode {

    @Specialization
    public boolean doOr(VirtualFrame f, boolean a, boolean b) {
        return a || b;
    }

    @Specialization()
    protected Object fallback(Object left, Object right) {
        throw new PhpException("OR can not be applied to operands", this);
    }
}

