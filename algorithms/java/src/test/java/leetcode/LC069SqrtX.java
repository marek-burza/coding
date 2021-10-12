package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/sqrtx/
 * #easy
 */
public final class LC069SqrtX {
    public final class Solution {
        public int mySqrt(final int x) {
            long a = 0;
            long z = x;
            while (a + 1 < z) {
                long m = (a + z) / 2;
                long mm = m * m;
                if (mm == x) {
                    return (int) m;
                }
                if (mm < x) {
                    a = m;
                } else {
                    z = m - 1;
                }
            }
            if (z * z <= x)
                return (int) z;
            else
                return (int) a;
        }
    }

    @Test
    public void test_64() throws Exception {
        assertEquals(8, new Solution().mySqrt(64));
    }

    @Test
    public void test_2() throws Exception {
        assertEquals(1, new Solution().mySqrt(2));
    }
}
