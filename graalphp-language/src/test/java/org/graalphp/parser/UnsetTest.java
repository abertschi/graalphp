package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.nodes.PhpStmtNode;
import org.graalphp.runtime.PhpUnsetNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class UnsetTest {

    @Test
    public void unsetParsing() throws Exception {
        Program pgm = TestCommons.parseProgram("$a = 1900; print($a); unset($a); print($a);",
                true);
        Assert.assertNotNull(pgm);
        System.out.println(pgm);
    }

    @Test
    public void unsetAst() throws Exception {
        Program pgm = TestCommons.parseProgram("$a = 1900; print($a); unset($a); print($a);",
                true);
        StmtVisitor visitor = new StmtVisitor(null);
        StmtVisitor.StmtVisitorContext phpAst =
                visitor.createPhpAst(pgm, ParseScope.newGlobalScope());
        boolean containsUnset = false;
        for (PhpStmtNode s : phpAst.getStmts()) {
            if (s instanceof PhpUnsetNode) {
                containsUnset = true;
            }
            System.out.println(s.toString());
        }
        if (!containsUnset) {
            Assert.fail("does not contain unset node");
        }
        Assert.assertTrue(phpAst.getStmts().size() > 0);
    }

    @Test(expected = Exception.class)
    public void testSimpleUnset() {
        TestCommons.compareStdout("1900", "$a = 1900; print($a); unset($a); print($a);", true);
    }

    @Test(expected = Exception.class)
    public void testComplexUnset() {
        TestCommons.compareStdout("1900", "$b = $a = 1900; print($a + $b); unset($a, $b); print($a + $b);", true);
    }

    @Test
    public void testUnsetOfNonExistingVariable() {
        TestCommons.compareStdout("", "unset($a);", true);
        TestCommons.compareStdout("", "unset($a, $b);", true);
    }

    @Test(expected = Exception.class)
    public void testInvalidUnset2() {
        TestCommons.compareStdout("", "function a() {return 1;}; unset(a());", true);

    }
}
