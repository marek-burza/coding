package leetcode.lc342_power_of_four;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/power-of-four/
 * #easy
 */
public final class LC342PowerOfFour {
    public final class Solution {
        public boolean isPowerOfFour(final int num) {
            if (num <= 0) return false;
            double value = Math.log(num) / Math.log(4);
            return value == Math.floor(value);
        }
    }

    @Test
    public void test_16() throws Exception {
        assertEquals(true, new Solution().isPowerOfFour(16));
    }

    @Test
    public void test_5() throws Exception {
        assertEquals(false, new Solution().isPowerOfFour(5));
    }
}
