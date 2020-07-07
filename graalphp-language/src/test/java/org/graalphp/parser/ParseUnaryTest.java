package org.graalphp.parser;

import org.junit.Test;

/**
 * @author abertschi
 */
public class ParseUnaryTest {

    @Test
    public void testNegateSimple() {
        TestCommons.evalInteger(0, "-0");
        TestCommons.evalInteger(0, "-(-0)");
        TestCommons.evalInteger(0, "-(-(-(-(-0))))");
        TestCommons.evalInteger(-1, "-(-(-(-(-1))))");
    }

    @Test
    public void testNegUnderflow() {
        TestCommons.evalDouble((double) Long.MIN_VALUE * -1.0, String.format("- %d ",
                Long.MIN_VALUE));

        // this causes a parser error
        // parser cannot handler larger than 8 bytes numbers, which is fine
        // neither can java with primitive types
        // TestCommons.evalInteger(0, String.format("-%d ", Long.MIN_VALUE));
    }

    @Test
    public void testOverflowUnary() {
        System.out.println(Long.MIN_VALUE);
        double smallerThanMin = Long.MIN_VALUE;
        smallerThanMin--;
        double largerThanMax = Long.MAX_VALUE;
        largerThanMax++;

        TestCommons.evalDouble(smallerThanMin, "$a = " + Long.MIN_VALUE + "; $a--; print $a;");
        TestCommons.evalDouble(largerThanMax, "$a = " + Long.MAX_VALUE + "; $a++; print $a;");

        TestCommons.evalDouble(smallerThanMin, "$a = " + Long.MIN_VALUE + "; --$a; print $a;");
        TestCommons.evalDouble(largerThanMax, "$a = " + Long.MAX_VALUE + "; ++$a; print $a;");
    }
}
