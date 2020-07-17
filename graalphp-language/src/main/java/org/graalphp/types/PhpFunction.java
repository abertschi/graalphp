package org.graalphp.types;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.utilities.CyclicAssumption;
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

    /**
     * Function Name
     */
    private String name;

    /**
     * Truffle Call Target
     */
    private RootCallTarget target;

    /**
     * Scope representing function body
     */
    private ParseScope scope;

    /**
     * True if function returns a reference (has & keyword in definition)
     */
    private boolean returnReference = false;

    private final CyclicAssumption callTargetStable;

    // is true if target is set properly
    private boolean init;

    public PhpFunction(String name, ParseScope scope, RootCallTarget target) {
        this.name = name;
        this.scope = scope;
        this.target = target;
        this.init = true;
        this.callTargetStable = new CyclicAssumption(name);
    }

    public PhpFunction(PhpLanguage lang, String name) {
        this.target = Truffle.getRuntime().createCallTarget(new UndefFunctionRootNode(lang, name));
        this.init = false;
        this.name = name;
        this.scope = null;
        this.callTargetStable = new CyclicAssumption(name);

    }

    public boolean isInit() {
        return init;
    }

    public RootCallTarget getCallTarget() {
        return this.target;
    }

    public Assumption getCallTargetStable() {
        return callTargetStable.getAssumption();
    }

    public void setCallTarget(RootCallTarget target) {
        this.init = true;
        this.target = target;
        callTargetStable.invalidate();
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

    public void setReturnReference(boolean b) {
        this.returnReference = b;
    }

    public boolean isReturnReference() {
        return this.returnReference;
    }

}
