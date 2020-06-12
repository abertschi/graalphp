package org.graalphp.parser;

import org.graalvm.polyglot.Context;
import org.junit.Assert;

/**
 * @author abertschi
 */
public class TestCommons {

    public static String php(String stmts) {
        return "<?php " + stmts + "?>";
    }

    public static void evalLong(long expected, String src) {
        Context ctx = Context.create("php");
        Assert.assertSame(expected, ctx.eval("php", php(src)).asLong());
    }
}
