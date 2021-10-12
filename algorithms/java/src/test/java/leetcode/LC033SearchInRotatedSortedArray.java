package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * #medium
 */
public class LC033SearchInRotatedSortedArray {
    public final class Solution {
        public int search(int[] nums, int target) {
            int a = 0;
            int z = nums.length - 1;
            while (a <= z) {
                int m = (a + z) >> 1;
                if (nums[m] == target) return m;
                if (nums[m] < target) {
                    if (target <= nums[z] || nums[a] < nums[m]) {
                        a = m + 1;
                    } else {
                        z = m - 1;
                    }
                } else {
                    if (nums[a] <= target || nums[m] < nums[z]) {
                        z = m - 1;
                    } else {
                        a = m + 1;
                    }
                }
            }
            return -1;
        }
    }

    @Test
    public void test_example() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        assertEquals(5, new Solution().search(nums, 1));
    }

    @Test
    public void test_single() {
        int[] nums = {1};
        assertEquals(0, new Solution().search(nums, 1));
    }

}
