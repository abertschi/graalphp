package org.graalphp.language;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class BinaryTest {

    @Test
    public void parseAddIntegrated() {
        Context ctx = Context.create("php");
        String code = TestCommons.php("1 + (2 + 4) ;");
        Value obj = ctx.eval("php", code);
        System.out.println(code + " = " + obj.asLong());
        Assert.assertSame(7, obj.asInt());
    }

    @Test
    public void parseAddIntegrated2() {
        Context ctx = Context.create("php");
        Assert.assertSame(7, ctx.eval("php", TestCommons.php("1 + (2 + 4) ;")).asInt());
        Assert.assertSame(10, ctx.eval("php", TestCommons.php("1 + 1 + 1 + 1 + (2 + 4);")).asInt());
        Assert.assertSame(22, ctx.eval("php", TestCommons.php("(((1 + 2) + 3) + (1 + 2) + 3) + 10 ;")).asInt());
        Assert.assertSame(2, ctx.eval("php", TestCommons.php("1 + (2 + 4); 1 + 1")).asInt());
        Assert.assertSame(10, ctx.eval("php", TestCommons.php("(1 + 2) + 1 + (2 + 4) ;")).asInt());
        Assert.assertSame(3, ctx.eval("php", TestCommons.php("(1 + 2); 1 + 2;  1 + 2;  1 + 2;")).asInt());
    }


    @Test
    public void parseSubSimple() {
        TestCommons.evalInteger(0, "1 - 1");
        TestCommons.evalInteger(0, "0 - 0");
        TestCommons.evalInteger(-8, "-1 - 10 - (-1 -2)");
        TestCommons.evalInteger(-7, "(-1 - 10) - -1 - (-1 -2)");

        // test infix
        TestCommons.evalInteger(2, "1 - (-1)");
    }

    @Test
    public void parseMulSimple() {
        TestCommons.evalInteger(1, "1 * 1");
        TestCommons.evalInteger(0, "0 * 0");
        TestCommons.evalInteger(0, "-0 * 0");
        TestCommons.evalInteger(0, "-0 * -0");
        TestCommons.evalInteger(1, "-1 * -1");
        TestCommons.evalInteger(-1, "1 * -1");
        TestCommons.evalInteger(-1, "-1 * 1");
        TestCommons.evalInteger(36, "2 * 2 * (3 * 3)");
        TestCommons.evalInteger(0, "2 * 2 * (3 * 3) * (0)");
    }

    @Test
    public void parseDivSimple() {
        TestCommons.evalInteger(1, "1 / 1");
        TestCommons.evalInteger(1, "-1 / -1");
        TestCommons.evalInteger(-1, "1 / -1");
        TestCommons.evalInteger(-1, "-1 / 1");
        TestCommons.evalInteger(1, "2 / 2 / (3 / 3)");
        TestCommons.evalInteger(0, "2 / 2 / (3 / 3) * (0)");
    }


    @Test(expected = RuntimeException.class)
    public void parseDivException1() {
        TestCommons.evalInteger(1337, "0 / 0");
    }

    @Test
    public void parseSubOverflow() {
        TestCommons.evalDouble((double) Long.MIN_VALUE - 1.0, String.format("%d - %d", Long.MIN_VALUE, -1));
    }

    @Test
    public void parseMulOverflow() {
        TestCommons.evalDouble(1E23, "1000000000000 * 100000000000");
        TestCommons.evalDouble(100.0, "10.0 * 10.0");
        TestCommons.evalInteger(0, "1000000000000000 * 0");
    }

    @Test
    public void parseMulOverflowMin() {
        // This overflows long, we convert to double
        TestCommons.evalDouble((double) Long.MIN_VALUE * -1.0,
                String.format("%d * -1", Long.MIN_VALUE));
    }

    @Test
    public void parseAst() throws Exception {
        String code = TestCommons.php("1 - (-1)");
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        parser.addErrorListener(new ConsoleErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
    }
}
