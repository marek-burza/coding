package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/maximum-subarray/
 */
public class LC053MaximumSubarray {
    public class Solution {
        public int maxSubArray(int[] nums) {
            int[] sums = new int[nums.length];
            int[] mins = new int[nums.length];
            mins[0] = 0;
            sums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sums[i] = sums[i - 1] + nums[i];
                mins[i] = sums[i - 1] < mins[i - 1] ? sums[i - 1] : mins[i - 1];
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int delta = sums[i] - mins[i];
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
