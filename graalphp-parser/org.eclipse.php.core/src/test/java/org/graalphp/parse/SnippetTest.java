package org.graalphp.parse;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Test;

import java.io.IOException;

/**
 * @author abertschi
 */
public class SnippetTest {

    //    @Test
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
}
