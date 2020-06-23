package org.graalphp.parser;

import org.junit.Test;

/**
 * @author abertschi
 */
public class BooleanTypeTest {

    @Test
    public void testBool() {
        TestCommons.evalBoolean(true, "$a = true;");
        TestCommons.evalBoolean(false, "$a = false;");
        TestCommons.evalInteger(1, "$a = false; $a + 1");
    }
}

