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
package leetcode.lc279_perfect_squares;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC279PerfectSquaresTests {
    @Test
    public void test12() throws Exception {
        assertEquals(3, new LC279PerfectSquares().numSquares(12));
    }

    @Test
    public void test13() throws Exception {
        assertEquals(2, new LC279PerfectSquares().numSquares(13));
    }

    @Test
    public void test9975() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(9975));
    }

    @Test
    public void test9732() throws Exception {
        assertEquals(3, new LC279PerfectSquares().numSquares(9732));
    }

    @Test
    public void test5756() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(5756));
    }

    @Test
    public void test6255() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(6255));
    }
}
