package org.graalphp.runtime;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.parser.ParseScope;
import org.graalphp.types.PhpNull;

import java.util.Arrays;

/**
 * Unset a variable.
 *
 * There is no way to remove a variable from a Frame.
 * We can only set it to Object type and treat it as undefined whenever we try to read it.
 *
 * @author abertschi
 */
@NodeField(name = "names", type = String[].class)
@NodeField(name = "parseScope", type = ParseScope.class)
public abstract class PhpUnsetNode extends PhpExprNode {

    protected abstract String[] getNames();

    protected abstract ParseScope getParseScope();

    /*
     * We currently implement a subset of the unset operator
     * https://github.com/php/php-langspec/blob/master/spec/11-statements.md#the-unset-statement
     *
     * Unset is defined on static, by-ref, global, properties, arrays, and variable scoped variables
     * Its implementation depends on the availability of these language feature
     *
     * We currently support;
     * - unset of global variables in global scope
     * - unset of local variables within functions
     *
     */
    @Specialization
    protected Object doUnset(VirtualFrame f) {
        for (String s : getNames()) {
            unset(f, s);
        }
        return PhpNull.SINGLETON;
    }

    private void unset(VirtualFrame f, String name) {
        FrameSlot frameSlot = getParseScope().resolveAndRemoveVariable(name);
        if (frameSlot == null) {
            // we ignore unset of non existing variables
            return;
        }
        f.getFrameDescriptor().removeFrameSlot(name);
        f.getFrameDescriptor().setFrameSlotKind(frameSlot, FrameSlotKind.Object);
        f.setObject(frameSlot, PhpUnset.SINGLETON);
    }

    @Override
    @TruffleBoundary
    public String toString() {
        return "PhpUnsetNode{" + Arrays.toString(getNames()) + "}";
    }
}
