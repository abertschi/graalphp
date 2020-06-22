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
        PhpFunction.class
})
public class PhpTypes {

    PhpTypes() {
    }

    // XXX: we allow a long to be converted to double where possible
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
