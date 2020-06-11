package org.graalphp.parser;

import org.junit.Test;

/**
 * @author abertschi
 */
public class ParseUnaryTest {

    @Test
    public void testNegateSimple() {
        TestCommons.evalLong(0, "-0");
        TestCommons.evalLong(0, "-(-0)");
        TestCommons.evalLong(0, "-(-(-(-(-0))))");
        TestCommons.evalLong(0, "-(-(-(-(-1))))");
    }
}
