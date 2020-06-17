package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import org.graalphp.PhpException;
import org.graalphp.PhpFunctionRegistry;
import org.graalphp.types.PhpFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Pojo to represent scoping in PHP
 *
 * @author abertschi
 */
public class ParseScope {

    private ParseScope global; // ref to self if this is global
    private FrameDescriptor frameDesc;

    // in current scope
    private Map<String, FrameSlot> vars;

    // functions in current scope
    private PhpFunctionRegistry functions;

    public ParseScope(FrameDescriptor frameDesc) {
        this.frameDesc = frameDesc;
        this.vars = new HashMap<>();
        this.functions = new PhpFunctionRegistry();
    }

    public ParseScope(FrameDescriptor frameDesc, ParseScope global) {
        this.frameDesc = frameDesc;
        this.vars = new HashMap<>();
        this.global = global;
        this.functions = new PhpFunctionRegistry();
    }


    // TODO: we currently do not support nested functions
    public PhpFunction resolveFunction(String name) {
        PhpFunction fn = this.functions.getFunction(name);
        if (fn == null && !isGlobalScope()) {
            return this.global.resolveFunction(name);
        }
        return fn;
    }

    public PhpFunctionRegistry getFunctions() {
        return this.functions;
    }

    public ParseScope getGlobal() {
        return global;
    }

    public void setGlobal(ParseScope global) {
        this.global = global;
    }

    public FrameDescriptor getFrameDesc() {
        return frameDesc;
    }

    public void setFrameDesc(FrameDescriptor frameDesc) {
        this.frameDesc = frameDesc;
    }

    public Map<String, FrameSlot> getVars() {
        return vars;
    }

    public void setVars(Map<String, FrameSlot> vars) {
        this.vars = vars;
    }

    public boolean isGlobalScope() {
        assert global != null : "global can never be null";
        return this.global == this;
    }
}
