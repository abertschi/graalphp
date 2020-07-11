package org.graalphp.types;

import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeCast;
import com.oracle.truffle.api.dsl.TypeSystem;
import org.graalphp.runtime.array.PhpArray;

/**
 * @author abertschi
 */
@TypeSystem({
        boolean.class,
        long.class,
        double.class,
        PhpArray.class,
        Object[].class
})
public abstract class PhpTypes {

    PhpTypes() {
    }

    @ImplicitCast
    public static double longToDouble(long val) {
        return (double) val;
    }

    @ImplicitCast
    public static long booleanToLong(boolean b) {
        return b ? 1 : 0;
    }

    @ImplicitCast
    public static double booleanToDouble(boolean b) {
        return b ? 1.0 : 0.0;
    }

    // XXX: more efficient way than default way which does boxing
    @TypeCast(double.class)
    public static double asDouble(Object value) {
        return ((Double) value).doubleValue();
    }

    @TypeCast(long.class)
    public static long asLong(Object value) {
        return ((Long) value).longValue();
    }
}
