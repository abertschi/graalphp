package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.types.PhpNull;

import java.util.Map;

/**
 * Root Node of the Truffle AST for PHP
 *
 * @author abertschi
 */
public final class PhpRootNode extends RootNode {

    // @Child
    private final PhpStmtNode root;

    // TODO: impl functions as hashmap

    public PhpRootNode(PhpLanguage language, PhpStmtNode rootStmt) {
        super(language);
        this.root = rootStmt;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO: return something useful
        Object res = PhpNull.SINGLETON;
        if (root != null) {
            root.executeVoid(frame);
        }
        return res;
    }
}
