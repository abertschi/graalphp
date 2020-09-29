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
 * Prints a value to stdout
 *
 * @author abertschi
 */
@NodeInfo(shortName = PrintBuiltin.NAME)
public abstract class PrintBuiltin extends PhpBuiltinNode {

    public static final String NAME = "print";

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

    @Specialization
    public String doPrintString(String val, @CachedContext(PhpLanguage.class) PhpContext ctx) {
        printString(ctx.getOutput(), val);
        return val;
    }

    @TruffleBoundary
    private static void printString(PrintWriter out, String value) {
        out.print(value);
        out.flush(); // TODO: increase performance
    }

    @TruffleBoundary
    private static void printLong(PrintWriter out, long value) {
        out.print(value);
        out.flush(); // TODO: increase performance
    }

    @TruffleBoundary
    private static void printDouble(PrintWriter out, double value) {
        out.print(value);
        out.flush(); // TODO: increase performance
    }
}
