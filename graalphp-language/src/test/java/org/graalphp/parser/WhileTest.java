package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Test;

/**
 * @author abertschi
 * See end to end test for more tests
 */
public class WhileTest {

    @Test
    public void testWhileSyntax() throws Exception {
        String src = TestCommons.php("while(1){print 1337;}");
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(src.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        StmtVisitor visitor = new StmtVisitor(null);
        System.out.println(pgm.toString());
        StmtVisitor.StmtVisitorContext phpAst = visitor.createPhpAst(pgm, ParseScope.newGlobalScope());
        for(PhpStmtNode s: phpAst.getStmts()) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void testWhile() {
        TestCommons.compareStdout("1111", "$i = 4; while($i > 0) {print 1; $i = $i -1;}" );
        TestCommons.compareStdout("", "$i = 4; while(false) {}" );
    }

    @Test
    public void testContinue() {
        TestCommons.compareStdout("1", "$i = 1; while($i > 0) {print 1; $i = 0; continue; print 2;}" );
    }

    @Test
    public void testBreak() {
        TestCommons.compareStdout("1", "while(true) {print 1; break; print 2;}" );
    }

    @Test(expected = Exception.class)
    public void testBreakNoLoop() {
        TestCommons.compareStdout("", "break;" );
    }

    @Test(expected = Exception.class)
    public void testContinueNoLoop() {
        TestCommons.compareStdout("", "continue;" );
    }

    @Test
    public void testDoWhile() {
        TestCommons.compareStdout("11111111111111111111", "$a = 20; do {print 1; $a = $a - 1;} while($a != 0);");
        TestCommons.compareStdout("1", "do {print 1;} while(false);");
        TestCommons.compareStdout("1", "do {print 1; break; } while(false);");
        TestCommons.compareStdout("1", "do {print 1; break; } while(true);");
    }
}
