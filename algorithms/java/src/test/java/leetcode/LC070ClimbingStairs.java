package leetcode;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * #easy
 */
public class LC070ClimbingStairs {
    public final class Solution {
        public int climbStairs(int n, int at, int[] lut) {
            if (at + 2 == n) {
                return 2;
            }
            if (at + 1 == n) {
                return 1;
            }
            if (lut[at] == 0) {
                lut[at] = climbStairs(n, at + 1, lut) + climbStairs(n, at + 2, lut);
            }
            return lut[at];
        }

        public int climbStairs(int n) {
            int[] lut = new int[n];
            Arrays.fill(lut, 0);
            return climbStairs(n, 0, lut);
        }
    }

    @Test
    public void test_20() throws Exception {
        assertEquals(10946, new Solution().climbStairs(20));
    }
}
