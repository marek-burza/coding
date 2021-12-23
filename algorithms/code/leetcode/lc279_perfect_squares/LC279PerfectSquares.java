package leetcode.lc279_perfect_squares;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/perfect-squares/
 * #medium
 */
public final class LC279PerfectSquares {
    public int numSquares(final int n) {
        int[] lut = new int[n + 1];
        Arrays.fill(lut, 1, n + 1, Integer.MAX_VALUE);
        for (int i = 1, ii = 1; ii <= n; ii = ++i * i) {
            for (int j = ii; j < lut.length; j++) {
                lut[j] = Math.min(lut[j], lut[j - ii] + 1);
            }
        }
        return lut[n];
    }
}
