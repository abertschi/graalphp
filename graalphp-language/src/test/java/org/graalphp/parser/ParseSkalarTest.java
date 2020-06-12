package org.graalphp.parser;

import org.graalvm.polyglot.Context;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author abertschi
 */
public class ParseSkalarTest {
    @Test
    public void parserSkalar() throws IOException {
        Context ctx = Context.create("php");
        Assert.assertSame(1, ctx.eval("php", TestCommons.php("1")).asInt());
        Assert.assertTrue(1000000000L == ctx.eval("php", TestCommons.php("1000000000")).asLong());
    }

    @Test
    public void parserSkalarTooBig() throws IOException {
        // TODO Test overflow
        Context ctx = Context.create("php");
//        Assert.assertSame(1, ctx.eval("php", php("-2147483648;")).asLong());
    }


}
