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
