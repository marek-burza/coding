package leetcode.lc029_divide_two_integers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC029DivideTwoIntegersTests {
    @Test
    public void testMinus1010369383Minus2147483648() throws Exception {
        LC029DivideTwoIntegers solution;
        solution = new LC029DivideTwoIntegers();
        assertEquals(-1010369383 / -2147483648, solution.divide(-1010369383, -2147483648));
    }

    @Test
    public void testMinus2147483648Minus1() throws Exception {
        LC029DivideTwoIntegers solution;
        solution = new LC029DivideTwoIntegers();
        assertEquals(Integer.MAX_VALUE, solution.divide(-2147483648, -1));
    }

    @Test
    public void testDivisor0() throws Exception {
        LC029DivideTwoIntegers solution;
        solution = new LC029DivideTwoIntegers();
        assertEquals(Integer.MAX_VALUE, solution.divide(1, 0));
    }

    @Test
    public void testDividend0() throws Exception {
        LC029DivideTwoIntegers solution;
        solution = new LC029DivideTwoIntegers();
        assertEquals(0, solution.divide(0, 1));
    }
}
