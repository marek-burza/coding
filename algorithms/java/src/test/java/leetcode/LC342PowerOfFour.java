package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/power-of-four/
 * #easy
 */
public class LC342PowerOfFour {
    public class Solution {
        public boolean isPowerOfFour(int num) {
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
