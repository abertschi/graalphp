package org.graalphp.builtins.language;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.CachedContext;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.PhpContext;
import org.graalphp.PhpLanguage;
import org.graalphp.builtins.PhpBuiltinNode;

import java.io.PrintWriter;

/**
 * Graalphp specific builtin for case in development where no strings are supported yet
 *
 * @author abertschi
 */
@NodeInfo(shortName = PrintlnBuiltin.NAME)
public abstract class PrintlnBuiltin extends PhpBuiltinNode {

    public static final String NAME = "println";

    @Specialization
    public long doPrintLong(long val, @CachedContext(PhpLanguage.class) PhpContext ctx) {
        printLong(ctx.getOutput(), val);
        return val;
    }

    @Specialization
    public double doPrintDouble(double val, @CachedContext(PhpLanguage.class) PhpContext ctx) {
        printDouble(ctx.getOutput(), val);
        return val;
    }

    @TruffleBoundary
    private static void printLong(PrintWriter out, long value) {
        out.println(value);
    }

    @TruffleBoundary
    private static void printDouble(PrintWriter out, double value) {
        out.println(value);
    }
}
