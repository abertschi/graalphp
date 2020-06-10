package org.graalphp.parse;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author abertschi
 */
public class SnippetTest {

    @Test
    public void testRef() throws Exception {
        String code = "<?php $c = &($a + $b); ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program ast = parser.parsePhpProgram();
        System.out.println(ast.toString());
    }

    @Test
    public void evalOrder() throws Exception {
        String code = "<?php $a + $b / $c ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program ast = parser.parsePhpProgram();
        System.out.println(ast.toString());
    }

    @Test
    public void nameOfName() throws Exception {
        String code = "<?php $a = 1; $$a = 2; ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program ast = parser.parsePhpProgram();
        System.out.println(ast.toString());
    }

    @Test
    public void callable() throws Exception {
        String code =
                "<?php  function doit($val = 2, callable $process) {" +
                        "    return $process($val);" +
                        "}" +
                        "$res = doit(5, function($p) {return $p * 2;});" +
                        "echo $res;" +
                        "?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program ast = parser.parsePhpProgram();
        System.out.println(ast.toString());
    }

    @Test
    public void underscoreIntNumber() throws Exception {
        String code = "<?php  $a =1_234_567; ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        System.out.println(ast.toString());
    }

    @Test
    public void hexNumber() throws Exception {
        String code = "<?php  $a = 0x1; ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        Assert.assertNotNull(ast);
        System.out.println(ast.toString());
    }

    @Test
    public void parseOctalNumber() throws Exception {
        // we parse octal as is, implementation needs to differentiate format of number, type is int
        String code = "<?php\n" +
                "        $a = 0123; // octal number (equivalent to 83 decimal)\n" +
                "?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        Assert.assertNotNull(ast);
        System.out.println(ast.toString());
    }

    @Test
    public void parseBinaryNumber() throws Exception {
        // type is bin
        String code = "<?php\n" +
                "        $a = 0b11111111; // binary number (equivalent to 255 decimal)\n" +
                "?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        Assert.assertNotNull(ast);
        System.out.println(ast.toString());
    }


    @Test
    public void parseOverflowInt() throws Exception {
        // type is bin
        String code = "<?php $a = 0; ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        Assert.assertNotNull(ast);
        System.out.println(ast.toString());
    }

    @Test
    public void intOverflow() throws Exception {
        String code = "<?php $largerThan4Bytes = 429496729700000000000000000000000000000; ?>";
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program ast = parser.parsePhpProgram();
        Assert.assertNotNull(ast);
        System.out.println(ast.toString());
    }
}


