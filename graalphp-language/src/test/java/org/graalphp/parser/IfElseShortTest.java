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
        StmtVisitor.StmtVisitorContext phpAst = visitor.createPhpAst(program, ParseScope.newGlobalScope());
        for(PhpStmtNode s: phpAst.getStmts()) {
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
}
