package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/reverse-integer/
 */
public class LC007ReverseInteger {
    public class Solution {
        public int reverse(int x) {
            long big = x, result = 0;
            int sign = 1;
            if (x < 0) {
                big = -big;
                sign = -1;
            }
            while (big > 0) {
                result *= 10;
                result += big % 10;
                big /= 10;
            }
            result *= sign;
            if (result < Integer.MIN_VALUE || Integer.MAX_VALUE < result) {
                return 0;
            }
            return (int) result;
        }
    }

    @Test
    public void test_1000000003() throws Exception {
        assertEquals(0, new Solution().reverse(1000000003));
    }

    @Test
    public void test_1534236469() throws Exception {
        assertEquals(0, new Solution().reverse(1534236469));
    }

    @Test
    public void test_Minus321() throws Exception {
        assertEquals(-123, new Solution().reverse(-321));
    }
}
