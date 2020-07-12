package org.graalphp.language;

import org.graalphp.PhpLanguage;
import org.graalvm.polyglot.Context;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author abertschi
 */
public class ScalarTest {
    @Test
    public void parserSkalar() throws IOException {
        PhpLanguage.RETURN_LAST_EXPR = true;
        Context ctx = Context.create("php");
        Assert.assertSame(1, ctx.eval("php", TestCommons.php("1")).asInt());
        Assert.assertTrue(1000000000L == ctx.eval("php", TestCommons.php("1000000000")).asLong());
    }

    @Test
    public void parserSkalarTooBig() {
        TestCommons.evalDouble(-2147483648, "-2147483648;");
        TestCommons.evalDouble(-2147483648000.0, "-2147483648000;");
    }


}
