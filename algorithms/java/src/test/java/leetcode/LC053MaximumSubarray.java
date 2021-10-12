package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * #easy
 */
public class LC053MaximumSubarray {
    public final class Solution {
        public int maxSubArray(int[] nums) {
            int sum = 0;
            int min = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                min = sum < min ? sum : min;
                sum += nums[i];
                int delta = sum - min;
                max = delta > max ? delta : max;
            }
            return max;
        }
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(1, new Solution().maxSubArray(new int[]{1}));
    }

    @Test
    public void test_Minus2_1() throws Exception {
        assertEquals(1, new Solution().maxSubArray(new int[]{-2, 1}));
    }

    @Test
    public void test_Minus2_Minus1() throws Exception {
        assertEquals(-1, new Solution().maxSubArray(new int[]{-2, -1}));
    }
}
