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

    @Test
    public void shiftLarge() {
        TestCommons.compareStdout("0", "print(123 >> 128)", true);
    }

    @Test
    public void shiftLargeNumbers() {
        TestCommons.compareStdout("-1024", "print(9223372036854775807 << 10)", true);
        TestCommons.compareStdout("9007199254740991", "print(9223372036854775807 >> 10)", true);
        TestCommons.compareStdout("9223372036854775807", "print(9223372036854775807 << 0)", true);
        TestCommons.compareStdout("9223372036854775807", "print(9223372036854775807 >> 0)", true);
        TestCommons.compareStdout("-4", "print(9223372036854775807 << 2)", true);
        TestCommons.compareStdout("2305843009213693951", "print(9223372036854775807 >> 2)", true);
        TestCommons.compareStdout("4611686018427387903", "print(9223372036854775807 >> 1)", true);
        TestCommons.compareStdout("-2", "print(9223372036854775807 << 1)", true);
    }

    @Test
    public void shiftSmallNumbers() {
        TestCommons.compareStdout("-9223372036854775808", "print(-9223372036854775808 >> 0)", true);
    }

    @Test
    public void shiftSmallNumbers2() {
        TestCommons.compareStdout("-2305843009213693952", "print(-9223372036854775808 >> 2)", true);
    }
}
