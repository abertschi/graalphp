package org.graalphp.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.PhpContext;
import org.graalphp.PhpException;
import org.graalphp.PhpLanguage;
import org.graalphp.parser.ParseScope;
import org.graalphp.types.PhpFunction;

/**
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
                throw new PhpException("Function " + name + " not found.", this);
            }
            this.function = fn;
        }

        return function;
    }
}
