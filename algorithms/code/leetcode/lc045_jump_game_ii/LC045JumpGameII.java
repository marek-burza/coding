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
package leetcode.lc045_jump_game_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC045JumpGameIITests {
    @Test
    public void testExample1() throws Exception {
        int[] nums = { 2, 3, 1, 1, 4 };
        assertEquals(2, new LC045JumpGameII().jump(nums));
    }

    @Test
    public void testNothing() throws Exception {
        int[] nums = { 0 };
        assertEquals(0, new LC045JumpGameII().jump(nums));
    }
}
