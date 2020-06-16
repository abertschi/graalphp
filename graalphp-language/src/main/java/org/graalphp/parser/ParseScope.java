package org.graalphp.parser;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

import java.util.HashMap;
import java.util.Map;

/**
 * Pojo to represent scoping in PHP
 *
 * @author abertschi
 */
public class ParseScope {

    private ParseScope global;

    private FrameDescriptor frameDesc;
    private Map<String, FrameSlot> vars;

    public ParseScope(FrameDescriptor frameDesc) {
        this.frameDesc = frameDesc;
        this.vars = new HashMap<>();
    }

    public ParseScope(FrameDescriptor frameDesc, ParseScope global) {
        this.frameDesc = frameDesc;
        this.vars = new HashMap<>();
        this.global = global;
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
}
