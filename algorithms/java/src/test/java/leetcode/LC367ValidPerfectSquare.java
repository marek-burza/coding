package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/valid-perfect-square/
 * #easy
 */
public class LC367ValidPerfectSquare {
    public class Solution {
        public boolean isPerfectSquare(int num) {
            long a = 0, z = 1 + num / 2;
            while (a <= z) {
                long m = (a + z) / 2;
                long mm = m * m;
                if (mm == num) {
                    return true;
                }
                if (mm > num) {
                    z = m - 1;
                } else {
                    a = m + 1;
                }
            }
            return false;
        }
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(true, new Solution().isPerfectSquare(1));
    }

    @Test
    public void test_14() throws Exception {
        assertEquals(false, new Solution().isPerfectSquare(14));
    }

    @Test
    public void test_16() throws Exception {
        assertEquals(true, new Solution().isPerfectSquare(16));
    }

    @Test
    public void test_maximum() throws Exception {
        assertEquals(false, new Solution().isPerfectSquare(Integer.MAX_VALUE));
    }
}
