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

    private static final int SRC_SECTION_NOT_SET = -1;

    /** index for source section from parser **/
    private int srcSectionStart = SRC_SECTION_NOT_SET;
    private int srcSectionLen = SRC_SECTION_NOT_SET;

    /** Stmts evaluate to nothing **/
    public abstract void executeVoid(VirtualFrame frame);

    public final void setSourceSection(int charLeft, int charRight) {
        assert (srcSectionStart == SRC_SECTION_NOT_SET);

        if (charLeft < 0) {
            throw new IllegalArgumentException("charleft < 0");
        } else if (charRight < 0) {
            throw new IllegalArgumentException("charRight < 0");
        }
        this.srcSectionStart = charLeft;
        this.srcSectionLen = charRight;
    }
}
