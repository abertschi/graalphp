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

    private final Map<String, RootCallTarget> functions;

    public PhpRootNode(PhpLanguage language,
                       RootCallTarget rootFunction,
                       Map<String, RootCallTarget> functions) {
        super(language);
        this.functions = functions;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO:
        return PhpNull.SINGLETON;
    }
}
