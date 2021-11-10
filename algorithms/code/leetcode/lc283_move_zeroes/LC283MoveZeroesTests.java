package leetcode.lc283_move_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC283MoveZeroesTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 0, 1, 0, 3, 12 };
        new LC283MoveZeroes().moveZeroes(nums);
        int[] expected = { 1, 3, 12, 0, 0 };
        assertArrayEquals(expected, nums);
    }
}
