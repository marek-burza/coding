package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/divide-two-integers/
 */
public class LC029DivideTwoIntegers {
    public class Solution {
        public int divide(int shortDividend, int shortDivisor) {
            long dividend = shortDividend;
            long divisor = shortDivisor;
            if (divisor == 0) {
                return Integer.MAX_VALUE;
            }
            if (dividend == 0) {
                return 0;
            }
            long sign = (dividend / Math.abs(dividend)) * (divisor / Math.abs(divisor));
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
            long counter = 0;
            while (dividend >= divisor) {
                long subtractor = divisor;
                long incrementor = 1;
                while (dividend - subtractor - subtractor >= 0) {
                    subtractor += subtractor;
                    incrementor += incrementor;
                }
                dividend -= subtractor;
                counter += incrementor;
            }
            if (sign * counter > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) (sign * counter);
        }
    }

    @Test
    public void test_Minus1010369383_Minus2147483648() throws Exception {
        assertEquals(-1010369383 / -2147483648, new Solution().divide(-1010369383, -2147483648));
    }

    @Test
    public void test_Minus2147483648_Minus1() throws Exception {
        assertEquals(Integer.MAX_VALUE, new Solution().divide(-2147483648, -1));
    }
}
