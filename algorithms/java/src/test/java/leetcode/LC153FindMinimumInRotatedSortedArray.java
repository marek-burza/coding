package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * #medium
 */
public final class LC153FindMinimumInRotatedSortedArray {
    public final class Solution {
        public int findMin(final int[] nums) {
            int a = 0, z = nums.length - 1;
            while (a != z) {
                // For sorted array return the smallest
                if (nums[a] < nums[z]) {
                    return nums[a];
                }
                // For only two elements pick the smaller
                if (z - a == 1) {
                    return Math.min(nums[a], nums[z]);
                }
                // Otherwise halve the search space
                int m = (a + z) / 2;
                if (nums[a] < nums[m]) {
                    a = m;
                }
                if (nums[m] < nums[z]) {
                    z = m;
                }
            }
            return nums[a];
        }
    }

    @Test
    public void test_0_1_2_4_5_6_7() throws Exception {
        int[] nums = {0, 1, 2, 4, 5, 6, 7};
        assertEquals(0, new Solution().findMin(nums));
    }

    @Test
    public void test_4_5_6_7_0_1_2() throws Exception {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(0, new Solution().findMin(nums));
    }

    @Test
    public void test_1_2() throws Exception {
        int[] nums = {1, 2};
        assertEquals(1, new Solution().findMin(nums));
    }

    @Test
    public void test_2_1() throws Exception {
        int[] nums = {2, 1};
        assertEquals(1, new Solution().findMin(nums));
    }
}
