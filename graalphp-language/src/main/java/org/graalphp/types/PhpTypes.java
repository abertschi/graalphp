package org.graalphp.types;

import com.oracle.truffle.api.dsl.ImplicitCast;
import com.oracle.truffle.api.dsl.TypeCast;
import com.oracle.truffle.api.dsl.TypeSystem;

/**
 * @author abertschi
 */
@TypeSystem({
        long.class,
        double.class
})
public class PhpTypes {

    // we allow a long to be converted to double where possible

    // TODO: this makes compiler crash
    @ImplicitCast
    public double longToDouble(long val) {
        return val;
    }

    // more efficient way than default way which does boxing
    @TypeCast(double.class)
    public static double asDouble(Object value) {
        return ((Double) value).doubleValue();
    }

    @TypeCast(long.class)
    public static double asLong(Object value) {
        return ((Long) value).longValue();
    }
}
