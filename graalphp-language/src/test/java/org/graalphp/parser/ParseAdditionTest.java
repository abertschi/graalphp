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
