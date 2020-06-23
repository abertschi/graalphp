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

    }
}
