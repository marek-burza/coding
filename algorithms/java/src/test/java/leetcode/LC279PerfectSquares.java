package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
public class LC279PerfectSquares {
    public class Solution {
        public int numSquares(int n) {
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

    @Test
    public void test_12() throws Exception {
        assertEquals(3, new Solution().numSquares(12));
    }

    @Test
    public void test_13() throws Exception {
        assertEquals(2, new Solution().numSquares(13));
    }

    @Test
    public void test_9975() throws Exception {
        assertEquals(4, new Solution().numSquares(9975));
    }

    @Test
    public void test_9732() throws Exception {
        assertEquals(3, new Solution().numSquares(9732));
    }

    @Test
    public void test_5756() throws Exception {
        assertEquals(4, new Solution().numSquares(5756));
    }

    @Test
    public void test_6255() throws Exception {
        assertEquals(4, new Solution().numSquares(6255));
    }
}
