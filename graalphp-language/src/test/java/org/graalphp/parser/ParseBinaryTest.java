package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class ParseBinaryTest {

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
        TestCommons.evalLong(0, "1 - 1");
        TestCommons.evalLong(0, "0 - 0");
        TestCommons.evalLong(-8, "-1 - 10 - (-1 -2)");
        TestCommons.evalLong(-7, "(-1 - 10) - -1 - (-1 -2)");

        // test infix
        TestCommons.evalLong(2, "1 - (-1)");
    }

    @Test
    public void parseMulSimple() {
        TestCommons.evalLong(1, "1 * 1");
        TestCommons.evalLong(0, "0 * 0");
        TestCommons.evalLong(0, "-0 * 0");
        TestCommons.evalLong(0, "-0 * -0");
        TestCommons.evalLong(1, "-1 * -1");
        TestCommons.evalLong(-1, "1 * -1");
        TestCommons.evalLong(-1, "-1 * 1");
        TestCommons.evalLong(36, "2 * 2 * (3 * 3)");
        TestCommons.evalLong(0, "2 * 2 * (3 * 3) * (0)");
    }

    @Test
    public void parseDivSimple() {
        TestCommons.evalLong(1, "1 / 1");
        TestCommons.evalLong(1, "-1 / -1");
        TestCommons.evalLong(-1, "1 / -1");
        TestCommons.evalLong(-1, "-1 / 1");
        TestCommons.evalLong(1, "2 / 2 / (3 / 3)");
        TestCommons.evalLong(0, "2 / 2 / (3 / 3) * (0)");
    }


    @Test(expected = PolyglotException.class)
    // TODO not yet implemented
    public void parseDivException1() {
        TestCommons.evalLong(0, "0 / 0");
    }



    @Test
    // TODO
    public void parseSubOverflow() {
        TestCommons.evalLong(0, String.format("%d - %d", Long.MIN_VALUE, -1));
    }

    @Test
    // TODO
    public void parseMulOverflow() {
        TestCommons.evalLong(0, "1000000000000 * 100000000000");
        TestCommons.evalLong(0, "1000000000000000 * 0");
    }

    @Test
    public void parseAst() throws Exception {
        String code = TestCommons.php("1 - (-1)");
        PhpParseVisitor v = new PhpParseVisitor();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
    }
}
