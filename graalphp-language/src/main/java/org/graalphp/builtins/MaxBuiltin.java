package org.graalphp.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * Max Builtin
 * @author abertschi
 */
@NodeInfo(shortName = MaxBuiltin.NAME)
public abstract class MaxBuiltin extends PhpBuiltinNode {

    public static final String NAME = "max";

    @Specialization
    protected long doMax(long a, long b) {
        return Math.max(a, b);
    }

    @Specialization
    protected double doMax(double a, double b) {
        return Math.max(a, b);
    }
}
