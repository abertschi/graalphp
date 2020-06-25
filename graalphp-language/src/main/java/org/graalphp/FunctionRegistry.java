package org.graalphp;

import org.graalphp.exception.PhpException;
import org.graalphp.types.PhpFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * A function invocation may appear above its function definition.
 * So during parsing of an invocation, we dont have a root calltarget
 * created yet for that function
 * <p>
 * This registry stores function definitions and enriches them with call targets
 * once their implementations are available
 *
 * @author abertschi
 */
public final class FunctionRegistry {

    private Map<String, PhpFunction> functions;

    public FunctionRegistry() {
        this.functions = new HashMap<>();
    }

    public PhpFunction getFunction(String name) {
        return this.functions.get(name);
    }

    public void register(String name, PhpFunction fn, boolean allowOverwrite) {
        boolean contains = this.functions.containsKey(name);
        if (contains) {
            if (!allowOverwrite) {
                throw new PhpException("Function already defined: " + name, null);
            }
            PhpFunction stored = this.functions.get(name);
            stored.setScope(fn.getScope());
            stored.setCallTarget(fn.getCallTarget());
        } else {
            this.functions.put(name, fn);
        }
    }
}
