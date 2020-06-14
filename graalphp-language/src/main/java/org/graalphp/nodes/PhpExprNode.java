package org.graalphp.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.types.PhpTypesGen;

/**
 * @author abertschi
 */
@NodeInfo(description = "Abstract class for expression")
public abstract class PhpExprNode extends PhpStmtNode {

    @Override
    public void executeVoid(VirtualFrame frame) {
        executeGeneric(frame);
    }

    /**
     * Execute method for no type specializations
     */
    public abstract Object executeGeneric(VirtualFrame frame);

    public long executeLong(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectLong(this.executeGeneric(f));
    }

    public double executeDouble(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectDouble(this.executeGeneric(f));
    }
}
