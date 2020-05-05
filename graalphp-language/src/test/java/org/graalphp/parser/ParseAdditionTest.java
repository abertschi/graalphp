package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParseAdditionTest {

    static String php(String stmts) {return "<?php " + stmts + "?>";}

    @Test
    public void parseAddIntegrated() throws IOException {
        Context ctx = Context.create("php");
        String code = php("1 + (2 + 4) ;");
        Value obj = ctx.eval("php", code);
        System.out.println(code + " = " + obj.asLong());
        Assert.assertSame(7, obj.asInt());
    }

    @Test
    public void parseAddIntegrated2() throws IOException {
        Context ctx = Context.create("php");
        Assert.assertSame(7, ctx.eval("php", php("1 + (2 + 4) ;")).asInt());
        Assert.assertSame(10, ctx.eval("php", php("1 + 1 + 1 + 1 + (2 + 4);")).asInt());
        Assert.assertSame(22, ctx.eval("php", php("(((1 + 2) + 3) + (1 + 2) + 3) + 10 ;")).asInt());
        Assert.assertSame(2, ctx.eval("php", php("1 + (2 + 4); 1 + 1")).asInt());
        Assert.assertSame(10, ctx.eval("php", php("(1 + 2) + 1 + (2 + 4) ;")).asInt());
        Assert.assertSame(3, ctx.eval("php", php("(1 + 2); 1 + 2;  1 + 2;  1 + 2;")).asInt());
    }

    public void parseAddSimple() throws IOException {
        String code = php("1 + (2 + 4) ;");
        PhpParseVisitor v = new PhpParseVisitor();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program pgm = null;
        try {
            pgm = parser.parsePhpProgram();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
