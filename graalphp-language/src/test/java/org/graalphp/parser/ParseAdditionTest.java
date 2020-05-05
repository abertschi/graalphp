package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Test;

import java.io.IOException;

public class ParseAdditionTest {

    static String php(String stmts) {return "<?php " + stmts + "?>";}

    @Test
    public void parseAssign1() throws IOException {
        Context ctx = Context.create("php");
        Value obj = ctx.eval("php", php("$a = 1;"));
    }

    public void foo() throws IOException {
        String code = "";
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
        System.out.println(pgm.toString());
        Object o = v.createGraalAst(pgm);
    }
}
