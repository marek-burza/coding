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
