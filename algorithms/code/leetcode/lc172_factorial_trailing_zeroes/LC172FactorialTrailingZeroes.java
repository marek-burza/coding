package leetcode.lc172_factorial_trailing_zeroes;

/**
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 * #medium
 */
public final class LC172FactorialTrailingZeroes {
    public int trailingZeroes(final int n) {
        long step = 5;
        long count = 0;
        while (step <= n) {
            count += n / step;
            step = step * 5;
        }
        return (int) count;
    }
}
