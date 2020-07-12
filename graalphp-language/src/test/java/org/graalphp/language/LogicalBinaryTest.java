package org.graalphp.language;

import org.junit.Test;

/**
 * @author abertschi
 */
public class LogicalBinaryTest {

    @Test
    public void testEqualsComplex0() {
        TestCommons.compareStdout("-2",
                "$A = array(array(1)); print ($A[0][0] == 1 ? -2 : 2);",
                true);
    }

    @Test
    public void testEqualsComplex1() {
        String code = "function itemCheck($treeNode) {" +
                "   return 1" +
                "      + ($treeNode[0][0] == -1 ? 1 : itemCheck($treeNode[0]))" +
                "      + ($treeNode[1][0] == -1 ? 1 : itemCheck($treeNode[1]));" +
                "}";
        TestCommons.compareStdout("3",
                "print(itemCheck(array(array(-1), array(-1)))); " + code,
                true);
    }

    @Test
    public void testEqualsSimple() {
        TestCommons.evalBoolean(true, "1 == 1");
        TestCommons.evalBoolean(false, "2 == 1");
        TestCommons.evalBoolean(true, "$a = 1; $a == 1");
        TestCommons.evalBoolean(false, "$a = 1; $b = 2; $a == $b");
        TestCommons.evalBoolean(true, "$a = 1; $b = 1.0; $a == $b");
        TestCommons.evalBoolean(true, "$a = 1.5; $b = 1.5; $a == $b");
        TestCommons.evalBoolean(false, "$a = 1.5; $b = 1.6; $a == $b");
    }

    @Test
    public void testNotEqualsSimple() {
        TestCommons.evalBoolean(false, "1 != 1");
        TestCommons.evalBoolean(true, "2 != 1");
        TestCommons.evalBoolean(false, "$a = 1; $a != 1");
        TestCommons.evalBoolean(true, "$a = 1; $b = 2; $a != $b");
        TestCommons.evalBoolean(false, "$a = 1; $b = 1.0; $a != $b");
        TestCommons.evalBoolean(false, "$a = 1.5; $b = 1.5; $a != $b");
        TestCommons.evalBoolean(true, "$a = 1.5; $b = 1.6; $a != $b");
    }

    @Test
    public void testComparisons() {
        TestCommons.evalBoolean(false, "1 > 1");
        TestCommons.evalBoolean(true, "1 >= 1");
        TestCommons.evalBoolean(false, "1 < 1");
        TestCommons.evalBoolean(true, "1 <= 1");

        TestCommons.evalBoolean(false, "$a = 1; $b = $a + 1; $b <= 1");
        TestCommons.evalBoolean(false, "$a = 1; $b = $a + 1; $b < 1");
        TestCommons.evalBoolean(true, "$a = 1; $b = $a + 1; $b > 1");
        TestCommons.evalBoolean(true, "$a = 1; $b = $a + 1; $b >= 1");
    }

    @Test
    public void testShortCircuit() {
        TestCommons.evalBoolean(true, "1 && 1");
        TestCommons.compareStdout("1", "$a = 1; 0 && ($a = 0); print $a");
        TestCommons.compareStdout("0", "$a = 1; 0 || ($a = 0); print $a");

    }
}
