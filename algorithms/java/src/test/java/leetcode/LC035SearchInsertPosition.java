package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/search-insert-position/
 */
public class LC035SearchInsertPosition {
    public class Solution {
        public int searchInsert(int[] nums, int target) {
            int a = 0, z = nums.length;
            while (a != z) {
                int m = (a + z) / 2;
                if (nums[m] < target) {
                    a = m + 1;
                    continue;
                }
                if (nums[m] >= target) {
                    z = m;
                    continue;
                }
            }
            return z;
        }
    }

    @Test
    public void test__empty__5() throws Exception {
        int[] nums = {};
        assertEquals(0, new Solution().searchInsert(nums, 5));
    }

    @Test
    public void test__1_3_5_6__5() throws Exception {
        int[] nums = {1, 3, 5, 6};
        assertEquals(2, new Solution().searchInsert(nums, 5));
    }

    @Test
    public void test__1_3_5_6__2() throws Exception {
        int[] nums = {1, 3, 5, 6};
        assertEquals(1, new Solution().searchInsert(nums, 2));
    }

    @Test
    public void test__1_3_5_6__7() throws Exception {
        int[] nums = {1, 3, 5, 6};
        assertEquals(4, new Solution().searchInsert(nums, 7));
    }

    @Test
    public void test__1_3_5_6__0() throws Exception {
        int[] nums = {1, 3, 5, 6};
        assertEquals(0, new Solution().searchInsert(nums, 0));
    }
}
