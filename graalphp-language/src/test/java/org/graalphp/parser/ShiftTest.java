package org.graalphp.parser;

import org.junit.Test;

/**
 * see also end to end tests for more tests
 *
 * @author abertschi
 */
public class ShiftTest {

    @Test(expected = Exception.class)
    public void fail1() {
        TestCommons.compareStdout("", "1 << -1;", true);
    }

    @Test(expected = Exception.class)
    public void fail2() {
        TestCommons.compareStdout("", "1 >> -1;", true);
    }

    @Test
    public void shiftNothing() {
        TestCommons.compareStdout("1", "print(1 << 0);", true);
        TestCommons.compareStdout("1", "print(1 >> 0);", true);
    }
}
