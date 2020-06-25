package org.graalphp.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

/**
 * Graalphp specific builtin as we do not support arrays yet to implement
 * standard php time measurement functions
 *
 * @author abertschi
 */
@NodeInfo(shortName = TimeNsBuiltin.NAME)
public abstract class TimeNsBuiltin extends PhpBuiltinNode {

    public static final String NAME = "graalphp_time_ns";

    @Specialization
    public long timeNs(VirtualFrame frame) {
        return System.nanoTime();
    }
}
