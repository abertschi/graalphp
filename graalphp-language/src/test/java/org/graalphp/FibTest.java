package org.graalphp;

import org.graalphp.parser.TestCommons;
import org.junit.Test;

/**
 * @author abertschi
 */
public class FibTest {

    @Test
    public void testFib() {
        String src = TestCommons.inputStreamToString(getClass()
                .getResourceAsStream("fib.php"));

        TestCommons.compareStdout("6765", src, false);

    }
}
