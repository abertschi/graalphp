package org.graalphp;

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
// TODO
public class FunctionRegistry {

    private Map<String, PhpFunction> functions;

    public FunctionRegistry() {
        this.functions = new HashMap<>();
    }

    public PhpFunction getFunction(String name) {
        return this.functions.get(name);
    }

    public void registerOrUpdate(String name, PhpFunction fn, boolean update) {
        boolean contains = this.functions.containsKey(name);
        if (update && contains) {
            PhpFunction stored = this.functions.get(name);
            stored.setScope(fn.getScope());
            stored.setCallTarget(fn.getCallTarget());
        } else if (!contains) {
            this.functions.put(name, fn);
        }
    }
}
