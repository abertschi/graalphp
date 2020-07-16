package org.graalphp.builtins.language;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import org.graalphp.PhpContext;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.array.ExecuteValuesNode;
import org.graalphp.types.PhpNull;

import java.io.PrintWriter;

/**
 * Builtin which prints a string along with values
 * This is used for benchmarks as long as we do not have support for strings
 *
 * @author abertschi
 */
@NodeField(name = "title", type = String.class)
@NodeChild(value = "values", type = ExecuteValuesNode.class)
public abstract class PrintArgsBuiltin extends PhpExprNode {

    public static final String NAME = "graalphp_print_args";

    protected abstract String getTitle();

    @Specialization
    public Object execute(Object[] vals, @CachedContext(PhpLanguage.class) PhpContext ctx) {
        print(ctx.getOutput(), vals);
        return PhpNull.SINGLETON;
    }

    @TruffleBoundary
    private void print(PrintWriter out, Object[] values) {
        StringBuilder build = new StringBuilder();
        build.append(getTitle()).append(";");
        for (Object o : values) {
            build.append(o.toString()).append(";");
        }
        out.println(build.toString());
    }

}
