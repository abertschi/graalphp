package org.graalphp.parser;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Assert;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author abertschi
 */
public class TestCommons {

    public static String php(String stmts) {
        return "<?php " + stmts + "?>";
    }

    private static final double DELTA = 0000000000.1;

    public static double evalInteger(long expected, String src) {
        Context ctx = Context.create("php");

        Value val = ctx.eval("php", php(src));
        System.out.println(val.toString());
        if (val.fitsInLong()) {
            Assert.assertEquals(expected, val.asLong());
        } else {
            Assert.assertEquals(expected, val.asDouble(), DELTA);
        }
        return val.asDouble();
    }

    public static double evalDouble(double expected, String src) {
        Context ctx = Context.create("php");
        Value val = ctx.eval("php", php(src));
        Assert.assertEquals(expected, val.asDouble(), DELTA);
        return val.asDouble();
    }

    public static String inputStreamToString(InputStream in ) {
        String text = null;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;
    }
}
