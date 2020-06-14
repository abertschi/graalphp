package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.types.PhpNull;

/**
 * Root Node of the Truffle AST for PHP
 *
 * @author abertschi
 */
// TODO: rewrite this to use stmts
public final class PhpRootNode extends RootNode {

    @Children
    private final PhpExprNode[] bodyNodes;

    // TODO: impl functions as hashmap
    public PhpRootNode(PhpLanguage language, PhpExprNode[] bodyNodes) {
        super(language);
        this.bodyNodes = bodyNodes;
    }

    @ExplodeLoop
    @Override
    public Object execute(VirtualFrame frame) {
        if (this.bodyNodes.length == 0) {
            return PhpNull.SINGLETON;
        }
        int last = this.bodyNodes.length - 1;
        CompilerAsserts.compilationConstant(last);
        for (int i = 0; i < last; i++) {
            this.bodyNodes[i].executeVoid(frame);
        }
        return this.bodyNodes[last].executeGeneric(frame);
    }
}
