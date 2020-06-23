package org.graalphp.parser;

import org.junit.Test;

/**
 * @author abertschi
 */
public class LogicalBinaryTest {

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
}
