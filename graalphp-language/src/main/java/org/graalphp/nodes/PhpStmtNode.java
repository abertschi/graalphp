package org.graalphp.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.types.PhpTypes;

/**
 *
 * @author abertschi
 *
 */

@NodeInfo(
        language = "Graal PHP Language",
        description = "Abstract base node for all PHP nodes")

@TypeSystemReference(PhpTypes.class)
// TODO: should we implement classes as subclass of this?
public abstract class PhpStmtNode extends Node {

    /** Stmts evaluate to nothing **/
    public abstract void executeVoid(VirtualFrame frame);
}
