package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/jump-game/
 * #medium
 */
public class LC055JumpGame {
    public final class Solution {
        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) {
                return false;
            }
            if (nums.length == 1) {
                return true;
            }
            int front = 0;
            for (int i = 0; i <= front; i++) {
                if (front >= nums.length - 1) return true;
                if (i == front && nums[front] == 0) return false;
                if (front < i + nums[i]) front = i + nums[i];
            }
            return false;
        }
    }

    @Test
    public void test_25002_integers() throws Exception {
        int[] nums1 = new int[25003];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = 25000 - i;
        }
        nums1[25000] = 1;
        nums1[25001] = 0;
        nums1[25002] = 0;
        assertEquals(false, new Solution().canJump(nums1));
    }

    @Test
    public void test_1_2_3() throws Exception {
        int[] nums2 = {1, 2, 3};
        assertEquals(true, new Solution().canJump(nums2));
    }
}
