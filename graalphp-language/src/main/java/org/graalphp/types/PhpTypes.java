package org.graalphp.types;

import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeCast;
import com.oracle.truffle.api.dsl.TypeSystem;

/**
 * @author abertschi
 */
@TypeSystem({
        long.class,
        double.class,
        boolean.class,
        PhpFunction.class
})
public class PhpTypes {

    PhpTypes() {
    }

    @ImplicitCast
    public static boolean longToBoolean(long val) {
        // XXX: according to 08-conversions.md#converting-to-boolean-type
        return val != 0;
    }

    @ImplicitCast
    public static boolean doubleToBoolean(double val) {
        // XXX: according to 08-conversions.md#converting-to-boolean-type
        return val != 0.0;
    }

    @ImplicitCast
    public static long booleanToLong(boolean b) {
        return b ? 1 : 0;
    }

    @ImplicitCast
    public static double booleanToDouble(boolean b) {
        return b ? 1.0 : 0.0;
    }

    @ImplicitCast
    public static double longToDouble(long val) {
        return (double) val;
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
