package org.graalphp.language;

import org.junit.Test;

/**
 * @author abertschi
 */
public class TernaryOperatorTest {

    // TestCommons.compareStdout("2", "print TRUE ? 1 : TRUE ? 2 : 3;", true);
    // What happens according to spec:
    // left to right
    // TestCommons.compareStdout("2", "print (TRUE ? 1 : TRUE) ? 2 : 3;", true);

    // What most expect:
    // right to left associative
    // TestCommons.compareStdout("2", "print TRUE ? 1 : (TRUE ? 2 : 3);", true);

    @Test(expected = Exception.class)
    public void testDeprecatedBehavior() {
        TestCommons.compareStdout("2", "print TRUE ? 1 : TRUE ? 2 : 3;", true);
    }

    @Test
    public void testRightAssoc() {
        TestCommons.compareStdout("2", "print (TRUE ? 1 : TRUE) ? 2 : 3;", true);
    }

    @Test
    public void testLeftAssoc() {
        TestCommons.compareStdout("1", "print TRUE ? 1 : (TRUE ? 2 : 3);", true);
    }


    @Test
    public void parserOutput() throws Exception {
        System.out.println(TestCommons.parseProgram("((1 ? 2 : 3) ? 4 : 5);", true)); //4
        System.out.println(TestCommons.parseProgram("1 ? 2 ? 3 : 4 : 5;", true)); // 4
    }
}
