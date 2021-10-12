package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/search-for-a-range/
 * #medium
 */
public class LC034SearchForARange {
    public final class Solution {
        private int bsInfimum(int[] nums, int target) {
            int a = 0;
            int z = nums.length - 1;
            while (a < z) {
                int m = (a + z) / 2;
                if (nums[m] < target) {
                    a = m + 1;
                }
                if (nums[m] == target) {
                    z = m;
                }
                if (nums[m] > target) {
                    z = m - 1;
                }
            }
            if (a == z && nums[a] == target) {
                return a;
            } else {
                return -1;
            }
        }

        private int bsSupremum(int[] nums, int target) {
            int a = 0;
            int z = nums.length - 1;
            while (a < z) {
                int m = (1 + a + z) / 2;
                if (nums[m] < target) {
                    a = m + 1;
                }
                if (nums[m] == target) {
                    a = m;
                }
                if (nums[m] > target) {
                    z = m - 1;
                }
            }
            if (a == z && nums[a] == target) {
                return a;
            } else {
                return -1;
            }
        }


        public int[] searchRange(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
            }
            return new int[]{bsInfimum(nums, target), bsSupremum(nums, target)};
        }
    }

    @Test
    public void test_5_7_7_8_8_10__8() throws Exception {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] expected = {3, 4};
        assertArrayEquals(expected, new Solution().searchRange(nums, 8));
    }
}
