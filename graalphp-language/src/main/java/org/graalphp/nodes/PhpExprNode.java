package org.graalphp.nodes;

import com.oracle.truffle.api.dsl.Executed;
import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.graalphp.types.PhpTypes;
import org.graalphp.types.PhpTypesGen;

/**
 * @author abertschi
 */
@TypeSystemReference(PhpTypes.class)
@NodeInfo(description = "Abstract class for Stmts evaluating to a value")
public abstract class PhpExprNode extends PhpStmtNode {

    /**
     * Execute method for no type specializations
     */
    public abstract Object executeGeneric(VirtualFrame frame);

    @Override
    public void executeVoid(VirtualFrame frame) {
        executeGeneric(frame);
    }

    public long executeLong(VirtualFrame f) throws UnexpectedResultException {
        return PhpTypesGen.expectLong(this.executeGeneric(f));
    }
}
