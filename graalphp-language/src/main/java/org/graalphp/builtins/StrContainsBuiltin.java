package org.graalphp.builtins;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.util.Arrays;

/**
 * @author abertschi
 */
@NodeInfo(shortName = StrContainsBuiltin.NAME)
public abstract class StrContainsBuiltin extends PhpBuiltinNode {

    public static final String NAME = "str_contains";

    @Specialization
    protected boolean doString(String haystack, String needle) {
        return haystack.contains(needle);
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "StrContainsBuiltin{" + Arrays.toString(getArguments()) + "}";
    }
}
