package org.graalphp.types;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.interop.TruffleObject;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.function.UndefFunctionRootNode;
import org.graalphp.parser.ParseScope;

/**
 * Holds context of a function to call
 * <p>
 * Can be created without being backed by an implementation.
 *
 * @author abertschi
 */
public final class PhpFunction implements TruffleObject {

    // TODO: optimization: CyclicAssumption
    // TODO: truffleObject for interoperability?

    private String name;

    private RootCallTarget target;

    private ParseScope scope;

    // is true if target is set properly
    private boolean init;

    public PhpFunction(String name, ParseScope scope, RootCallTarget target) {
        this.name = name;
        this.scope = scope;
        this.target = target;
        this.init = true;
    }

    public PhpFunction(PhpLanguage lang, String name) {
        this.target = Truffle.getRuntime().createCallTarget(new UndefFunctionRootNode(lang, name));
        this.init = false;
        this.name = name;
        this.scope = null;
    }

    public boolean isInit() {
        return init;
    }

    public RootCallTarget getCallTarget() {
        return target;
    }

    public void setCallTarget(RootCallTarget target) {
        this.init = true;
        this.target = target;
    }

    public ParseScope getScope() {
        return scope;
    }

    public void setScope(ParseScope scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }
}
