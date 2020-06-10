package org.graalphp.scratch;

import org.junit.Test;

/**
 * @author abertschi
 */
public class VariaTest {

    @Test
    public void overflowDivLong() {
        // this overflows as multiplication is kept as ints:
        final long A = 24 * 60 * 60 * 1000 * 1000;
        final long B = 24 * 60 * 60 * 1000;
        System.out.println(A / B);
    }

    @Test
    public void floatTests() {
        long size = 1000;
        double a = 444, b = 13, c = 345, d = 39, e = 5, f = 45;
        for (int i = 0; i < size; i++) {
            a = (float) Math.sin(a);
            b = (float) Math.asin(b);
            c = (float) Math.sqrt(c);
            d = (float) d + d - d + d;
            e = (float) e * e + e * e;
            f = (float) f / f / f / f / f;
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

        a = 444;
        b = 13;
        c = 345;
        d = 39;
        e = 5;

        f = 45;
        for (int i = 0; i < size; i++) {
            a =  Math.sin(a);
            b =  Math.asin(b);
            c =  Math.sqrt(c);
            d =  d + d - d + d;
            e =  e * e + e * e;
            f =  f / f / f / f / f;
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
    }
}
