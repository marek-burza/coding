package leetcode.lc029_divide_two_integers;

/**
 * https://leetcode.com/problems/divide-two-integers/
 */
public final class LC029DivideTwoIntegers {
    public int divide(final int shortDividend, final int shortDivisor) {
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
