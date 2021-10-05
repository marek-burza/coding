package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * #medium
 */
public class LC045JumpGameII {
    public class Solution {
        public int jump(int[] nums) {
            if (nums.length == 1) return 0;
            int horizon = nums[0];
            int i = 0;
            int count = 1;
            while (horizon < nums.length - 1) {
                int replacement = horizon;
                while (i <= horizon) {
                    if (i + nums[i] > replacement) {
                        replacement = i + nums[i];
                    }
                    i++;
                }
                i--;
                horizon = replacement;
                count++;
            }
            return count;
        }
    }

    @Test
    public void test_2_3_1_1_4() throws Exception {
        int[] nums = {2, 3, 1, 1, 4};
        assertEquals(2, new Solution().jump(nums));
    }
}
