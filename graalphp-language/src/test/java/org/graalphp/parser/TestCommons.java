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

    public static double evalInteger(long expected, String src) {
        Context ctx = Context.create("php");

        Value val = ctx.eval("php", php(src));
        if (val.fitsInLong()) {
            Assert.assertSame(expected, val.asLong());
        } else {
            Assert.assertSame(expected, val.asDouble());
        }
        return val.asDouble();
    }

    public static double evalDouble(double expected, String src) {
        Context ctx = Context.create("php");
        Value val = ctx.eval("php", php(src));
        Assert.assertEquals(expected, val.asDouble(), 0000000000.1);
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
