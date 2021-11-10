package leetcode.lc283_move_zeroes;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/ #easy
 */
public final class LC283MoveZeroes {
    public void moveZeroes(final int[] nums) {
        int target = 0;
        for (int index = 0; index < nums.length; index++) {
            nums[target] = nums[index];
            if (nums[index] != 0) {
                target++;
            }
        }
        Arrays.fill(nums, target, nums.length, 0);
    }
}
