package org.graalphp.language;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.parser.ParseScope;
import org.graalphp.parser.StmtVisitor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class ArrayTest {

    @Test
    public void parserTest() throws Exception {
        String src = TestCommons.inputStreamToString(getClass().getResourceAsStream("fannkuch.bak" +
                ".php"));
        System.out.println(src);
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(src.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.StmtVisitorContext phpAst = visitor.createPhpAst(pgm,
                ParseScope.newGlobalScope());
        for (PhpStmtNode s : phpAst.getStmts()) {
            System.out.println(s.toString());
        }
        Assert.assertTrue(phpAst.getStmts().size() > 0);
    }

    @Test
    public void parseNestedArrays() throws Exception {
        String code = "$A = array(array()); $A[0][0] = 1;";
        Program program = TestCommons.parseProgram(code, true);
        System.out.println(program);
        System.out.println(TestCommons.createTruffleAst(program).getStmts());
    }

    @Test(expected = Exception.class)
    public void arrayInvalidIndex() {
        // TODO in order to be php compliant, this test later on must work, array must be
        //  converted to map
        String code = TestCommons.php("$a = array(); $a[-1] = 1;");
        TestCommons.compareStdout("", code, false);

    }

    @Test()
    public void arrayAccessSimple() {
        String code = TestCommons.php("$a = array(); $a[0] = 1337; print($a[0]);");
        TestCommons.compareStdout("1337", code, false);
    }

    @Test()
    public void arrayGeneralizeTest() {
        String code = TestCommons.php("$a = array(); $a[0] = 1337; $a[1] = 1.1; print($a[0]); " +
                "print($a[1])");
        TestCommons.compareStdout("13371.1", code, false);
    }

    @Test()
    public void arrayGeneralizeAndGrow() {
        String code = TestCommons.php("$a = array(); " +
                "for($i = 0; $i < 10000; $i ++){$a[$i] = 1337;}; $a[$i]  = 1.1; print($a[$i]);" +
                "for($i; $i < 10000; $i ++){$a[$i] = 1337;}; print($a[$i - 1]);");
        TestCommons.compareStdout("1.11337", code, false);
    }

    @Test
    public void arrayTestSpecialization() {
        String code = TestCommons.php("$n = 30; $A = array();" +
                "for($i = 0; $i < $n; $i ++) {" +
                " $A[$i] = $i;" +
                "}" +
                "for($i; $i <  (2 * $n); $i ++) {" +
                " $A[$i] = $i + 0.1;" +
                "}");
        TestCommons.compareStdout("", code, false);
    }

    @Test
    public void array2DTestSimple() {
        TestCommons.compareStdout("1337",
                "$A = array(); $B = array(); $B[0] = 1337; $A[0] = $B; print($A[0][0]);");
    }

    @Test
    public void arrayCopyByValue() {
        TestCommons.compareStdout("13371336",
                "$B = array(); $B[0] = 1337; $A = $B; $B[0] = 1336; print($A[0]); print($B[0]);");
    }

    // TODO: this is supposed to fail if php compliant
    @Test()
    public void arrayReadEmpty() {
        String code = TestCommons.php("$a = array();  print($a[0]);");
        TestCommons.compareStdout("0", code, false);

    }

    @Test
    public void testNestedAssign() {
        String code = "$B = array(10); $C = array($B, 20);  print($C[1]); $a = $C[0][0] = -1; " +
                "print($C[1]);  print($a);";
        TestCommons.compareStdout("2020-1", code, true);
    }

    @Test
    public void testOutOfBounds() {
        TestCommons.compareStdout("1", "$A = array(); $A[1000] = 1; print($A[1000]);");
    }

    @Test
    public void testOutOfBoundsDifferentType() {
        TestCommons.compareStdout("1.2", "$A = array(); $A[1000] = 1.2; print($A[1000]);");
    }

}
