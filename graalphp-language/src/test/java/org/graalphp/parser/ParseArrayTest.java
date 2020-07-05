package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class ParseArrayTest {

    @Test
    public void parserTest() throws Exception {
        String src = TestCommons.inputStreamToString(getClass().getResourceAsStream("fannkuch.bak.php"));
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


//    @Test
    public void fannkuchExecTest() {
        String src = TestCommons.inputStreamToString(getClass().getResourceAsStream("fannkuch.bak.php"));
        TestCommons.compareStdout("", src, false);
    }
}
