package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.PhpUndefFunctionException;
import org.graalphp.parser.ParseScope;
import org.graalphp.types.PhpFunction;

/**
 * Function implementation may only be known after a function call
 * in terms of location in source code.
 * <p>
 * During runtime, this node looks up the implementation of the function.
 *
 * @author abertschi
 */
public final class PhpFunctionLookupNode extends PhpExprNode {

    private final String name;

    // lazy load function because not yet available when this node is created
    @CompilationFinal
    private PhpFunction function;

    private final ParseScope scope;

    public PhpFunctionLookupNode(String name, ParseScope scope) {
        this.name = name;
        this.scope = scope;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        if (function == null) {
            // TODO: we could make this a new type which we execute and expect instead

            // We are about to change a @CompilationFinal field.
            CompilerDirectives.transferToInterpreterAndInvalidate();

            PhpFunction fn = this.scope.resolveFunction(this.name);
            if (fn == null) {
                StringBuilder buf = new StringBuilder();
                buf.append("Function ").append(name).append(" not found.").append(" scope: ")
                        .append(scope.getGlobal() == scope ? "<global>" : "<function>");
                throw new PhpUndefFunctionException(buf.toString(), this);
            }
            this.function = fn;
        }
        return function;
    }
}
