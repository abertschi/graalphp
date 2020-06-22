package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author abertschi
 */
public class ParseFunctionTest {

    @Test
    public void parseSimpleFn() throws Exception {
        String src = toString(getClass().getResourceAsStream("ParseFnTest01.php"));
        System.out.println(src);
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(src.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.StmtVisitorContext phpAst = visitor.createPhpAst(pgm, ParseScope.newGlobalScope());
        for(PhpStmtNode s: phpAst.getStmts()) {
            System.out.println(s.toString());
        }

        Assert.assertTrue(phpAst.getStmts().size() > 0);
    }

    @Test
    public void testSimpleStatic() {
        String src = "function foo() {return 1000; }; foo();";
        TestCommons.evalInteger(1000, src);
    }

    @Test
    public void testSimpleStaticAdd() {
        String src = "function foo() {return 1 + 1;}; foo();";
        TestCommons.evalInteger(2, src);
    }

    @Test
    public void testSimpleStaticEmptyArg() {
        String src = "function foo($a) {return 1 + 1;}; foo(1);";
        TestCommons.evalInteger(2, src);
    }

    @Test
    public void testSimpleArg() {
        String src = "function foo($a) {return $a;}; foo(1);";
        TestCommons.evalInteger(1, src);
    }

    @Test
    public void testCallOtherFn() {
        final String src = "function a() {return b();} function b() {return 1337;} a();";
        TestCommons.evalInteger(1337, src);

    }

    @Test
    public void testFnInvocations() {
        TestCommons.evalInteger(1, "function foo($a) {return $a;}; foo(1);");
        TestCommons.evalInteger(2, "function foo($a) {return $a + 1;}; foo(1);");
        TestCommons.evalInteger(2, "function foo($a) {return ($a + 1);}; foo(1);");
        TestCommons.evalInteger(1, "function foo($a) {return ($a * $a);}; foo(1);");
        TestCommons.evalInteger(16, "function foo($a) {return ($a * $a);}; foo(4);");
        TestCommons.evalInteger(25, "function foo($a) {return ($a * $a);}; foo(4 + 1);");
    }

    @Test
    public void testFnINestedInvocations() {
        TestCommons.evalInteger(4, "function foo($a) {return $a + 1;}; foo(1 + foo(1));");
    }



    private String toString(InputStream in) {
        String text = null;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;
    }
}
