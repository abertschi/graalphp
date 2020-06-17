package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Test;

/**
 * @author abertschi
 */
public class ParseAssignTest {

    @Test
    public void testAssignIntegerSuccess() {
        TestCommons.evalInteger(1, "$a = 1;");
        TestCommons.evalInteger(1, "$a = 1; $a; ");
        TestCommons.evalInteger(1, "$a = 1; $b = $a;");
        TestCommons.evalInteger(1, "$a = 1; $a = $a;");
        TestCommons.evalInteger(2, "$a = 1; $b = 2;");
        TestCommons.evalInteger(2, "$a = 1 + 1; $a");
    }

    @Test
    public void testAssignFloatSuccess() {
        TestCommons.evalDouble(1.5, "$a = 1.5;");
        TestCommons.evalDouble(3.5, "$a = 3.5; $a; ");
        TestCommons.evalDouble(3.5, "$a = 3.5; $b = $a;");
        TestCommons.evalDouble(3.5, "$a = 3.5; $a = $a;");
        TestCommons.evalDouble(3.6, "$a = 3.5; $b = 3.6;");
        TestCommons.evalDouble(1.7, "$a = 1.5 + 0.2; $a");
    }

    @Test
    public void testLastStmtIsAssignment() throws Exception {
        String src =
                "$i1 = 2;" +
                "$i1 = $i1 * 3; ";

        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(TestCommons.php(src).toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.PhpStmtVisitorContext phpAst = visitor.createPhpAst(pgm);

        for (PhpStmtNode s : phpAst.getStmts()) {
            System.out.println(s);
        }


//        TestCommons.evalInteger(6, src);


    }

    @Test(expected = Exception.class)
    public void testAssignFail() {
        TestCommons.evalInteger(1, "$a = $a;"); // undef var
    }


    private void tmp() throws Exception {
        String src = TestCommons.inputStreamToString(getClass().getResourceAsStream("simpleAssignment.php"));
        System.out.println(src);
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(src.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        // System.out.println(pgm);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.PhpStmtVisitorContext phpAst = visitor.createPhpAst(pgm);

        for (PhpStmtNode s : phpAst.getStmts()) {
            System.out.println(s);
        }
    }
}
