package leetcode.lc045_jump_game_ii;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * #medium
 */
public final class LC045JumpGameII {
    public int jump(final int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
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
