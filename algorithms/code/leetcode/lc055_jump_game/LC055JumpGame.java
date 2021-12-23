package leetcode.lc055_jump_game;

/**
 * https://leetcode.com/problems/jump-game/
 * #medium
 */
public final class LC055JumpGame {
    public boolean canJump(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int front = 0;
        for (int i = 0; /*i <= front*/; i++) {
            if (front >= nums.length - 1) {
                return true;
            }
            if (i == front && nums[front] == 0) {
                return false;
            }
            if (front < i + nums[i]) {
                front = i + nums[i];
            }
        }
        // return false;
    }
}
