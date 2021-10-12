package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/rotate-array/
 * #medium
 */
public class LC189RotateArray {
    public class Solution {
        private void reverse(int[] nums, int a, int b) {
            while (a < b) {
                int swap = nums[a];
                nums[a] = nums[b];
                nums[b] = swap;
                a++;
                b--;
            }
        }

        public void rotate(int[] nums, int k) {
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
    }

    @Test
    public void test_1_2_3_4_5_6_7__3() throws Exception {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        new Solution().rotate(nums, 3);
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test_1_2_3_4_5_6__2() throws Exception {
        int[] nums = {1, 2, 3, 4, 5, 6};
        new Solution().rotate(nums, 2);
        int[] expected = {5, 6, 1, 2, 3, 4};
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test_1__2() throws Exception {
        int[] nums = {1};
        new Solution().rotate(nums, 1);
        int[] expected = {1};
        assertArrayEquals(expected, nums);
    }
}
