package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * #hard
 */
public class LC154FindMinimumInRotatedSortedArrayII {
    public class Solution {
        public int findMin(int[] nums) {
            int a = 0;
            int z = nums.length - 1;
            while (a < z) {
                int m = (a + z) >> 1;
                if (nums[z] < nums[m]) {
                    a = m + 1;
                } else if (nums[m] < nums[z]) {
                    z = m;
                } else {
                    z--;
                }
            }
            return nums[a];
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(0, new Solution().findMin(nums));
    }

    @Test
    public void test_trickier() throws Exception {
        int[] nums = {1, 1, 0, 1, 1, 1, 1};
        assertEquals(0, new Solution().findMin(nums));
    }
}
