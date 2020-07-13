package org.graalphp.language;

import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class BuiltinTest {

    @Test
    public void parseBuiltin() throws Exception {
        Program pgm = TestCommons.parseProgram("foo(\"test\", 1);",
                true);
        Assert.assertNotNull(pgm);
        System.out.println(pgm);
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
