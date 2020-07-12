package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.junit.Test;

/**
 * @author abertschi
 */
public class IfElseShortTest {

    @Test
    public void testparse() throws Exception {
        String code = "1 ? print (1) : print(2);";
        Program program = TestCommons.parseProgram(code, true);
        System.out.println(program);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.StmtVisitorContext phpAst = visitor.createPhpAst(program,
                ParseScope.newGlobalScope());
        for (PhpStmtNode s : phpAst.getStmts()) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void testShortForm() throws Exception {
        TestCommons.compareStdout("1", "1 ? print (1) : print(2);", true);
        TestCommons.compareStdout("2", "0 ? print (1) : print(2);", true);
        TestCommons.compareStdout("1", "-1 ? print (1) : print(2);", true);
        TestCommons.compareStdout("1", "-1.1 ? print (1) : print(2);", true);
        TestCommons.compareStdout("1", "1 < 2 ? print (1) : print(2);", true);
        TestCommons.compareStdout("2", "1 > 2 ? print (1) : print(2);", true);
    }

    @Test(expected = Exception.class)
    public void testCoalesce1() throws Exception {
        // test unsupported behavior
        TestCommons.compareStdout("1", "1 ? : 2;", true);
    }

    @Test(expected = Exception.class)
    public void testCoalesce2() throws Exception {
        // test unsupported behavior
        TestCommons.compareStdout("2", "0 ? : 2;", true);
    }

    @Test(expected = Exception.class)
    public void testCoaleseSimple() throws Exception {
        // test unsupported behavior
        TestCommons.compareStdout("2", "0 ?? 2;", true);
    }

    @Test(expected = Exception.class)
    public void testCoaleseSimple2() throws Exception {
        // test unsupported behavior
        TestCommons.compareStdout("2", "0.0 ?? 2;", true);
    }

    @Test
    public void testComplex() {
        String code = "$i = 10; $a = $i++ ? f($i) : f(++$i); print($i); print($a);" +
                "function f($int) {return ($int > 1) ? $int * f($int - 1) : $int;};";
        TestCommons.compareStdout("1139916800", code, true);
    }

    @Test
    public void testMultiply() {
        final double DELTA = 1 << 5;
        String f = "function f($int) {return ($int > 1) ? $int * f($int - 1) : $int;};";
        TestCommons.evalDouble(1, "f(1); " + f);
        TestCommons.evalDouble(3628800, "f(10); " + f);
    }
}
