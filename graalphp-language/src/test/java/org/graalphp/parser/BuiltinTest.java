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
public class BuiltinTest {


    @Test
    public void parseBuiltin() throws Exception {
        String src = TestCommons.php("echo 1;");
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
    public void testPrint() {
        TestCommons.compareStdout("1337", "print (1337);");
        TestCommons.compareStdout("1337.0", "print 1337.0;");
    }

    @Test
    public void testEcho() {
//        TestCommons.compareStdout("1337.0", "echo 1337.0, 1;");
    }
}
