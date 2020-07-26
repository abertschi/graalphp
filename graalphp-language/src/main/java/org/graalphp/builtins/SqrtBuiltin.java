package org.graalphp.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.util.Arrays;

/**
 * @author abertschi
 */
@NodeInfo(shortName = SqrtBuiltin.NAME)
public abstract class SqrtBuiltin extends PhpBuiltinNode {

    public static final String NAME = "sqrt";

    @Specialization
    protected double doLong(double a) {
        return Math.sqrt(a);
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "SqrtBuiltin{" + Arrays.toString(getArguments()) + "}";
    }
}
