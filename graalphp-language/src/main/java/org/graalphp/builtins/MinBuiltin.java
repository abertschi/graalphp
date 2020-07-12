package org.graalphp.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * Min Builtin
 *
 * @author abertschi
 */
@NodeInfo(shortName = MinBuiltin.NAME)
public abstract class MinBuiltin extends PhpBuiltinNode {

    public static final String NAME = "min";

    @Specialization
    protected long doMin(long a, long b) {
        return Math.min(a, b);
    }

    @Specialization
    protected double doMin(double a, double b) {
        return Math.min(a, b);
    }
}
