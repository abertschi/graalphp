package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.types.PhpNull;

import java.util.Map;

public final class PhpRootNode extends RootNode {

    private final Map<String, RootCallTarget> functions;

    public PhpRootNode(PhpLanguage language, RootCallTarget rootFunction, Map<String, RootCallTarget> functions) {
        super(language);
        this.functions = functions;
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    protected boolean isInstrumentable() {
        return false;
    }

    @Override
    public String getName() {
        return "root eval";
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return PhpNull.SINGLETON;
    }
}
