package org.graalphp.builtins;

import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import org.graalphp.PhpContext;
import org.graalphp.PhpLanguage;

/**
 * @author abertschi
 */
@NodeField(name = "name", type = String.class)
public abstract class UnsetBuiltin extends PhpBuiltinNode {

    @Specialization
    public void doUnset(Frame frame,
                        @CachedContext(PhpLanguage.class) PhpContext context) {
        context.getRootScope()

    }

}
