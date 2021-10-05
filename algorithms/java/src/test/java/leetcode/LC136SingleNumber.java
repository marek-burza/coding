package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/single-number/
 * #easy
 */
public class LC136SingleNumber {
    public class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int value : nums) {
                result ^= value;
            }
            return result;
        }
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(1, new Solution().singleNumber(new int[]{1}));
    }

    @Test
    public void test_1_2_1() throws Exception {
        assertEquals(2, new Solution().singleNumber(new int[]{1, 2, 1}));
    }
}
