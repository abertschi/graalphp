package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Test;

/**
 * @author abertschi
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
}
