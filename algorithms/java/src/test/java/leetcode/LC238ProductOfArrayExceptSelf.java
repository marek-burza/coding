package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * #medium
 */
public class LC238ProductOfArrayExceptSelf {
    public class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] result = new int[nums.length];
            result[0] = 1;
            for (int i = 1; i < nums.length; i++) {
                result[i] = result[i - 1] * nums[i - 1];
            }
            int other = 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                other *= nums[i + 1];
                result[i] *= other;
            }
            return result;
        }
    }

    @Test
    public void test__1_2_3_4() throws Exception {
        int[] nums = {1, 2, 3, 4};
        int[] expected = {24, 12, 8, 6};
        int[] actual = new Solution().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void test__9_0_Minus2() throws Exception {
        int[] nums = {9, 0, -2};
        int[] expected = {0, -18, 0};
        int[] actual = new Solution().productExceptSelf(nums);
        assertArrayEquals(expected, actual);
    }
}
