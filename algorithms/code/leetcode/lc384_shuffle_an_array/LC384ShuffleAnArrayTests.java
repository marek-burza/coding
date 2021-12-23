package leetcode.lc384_shuffle_an_array;

import org.junit.jupiter.api.Test;

import leetcode.lc384_shuffle_an_array.LC384ShuffleAnArray.Solution;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC384ShuffleAnArrayTests {
    private void test(final int[] nums) {
        Solution solution = new Solution(nums.clone());
        int[] result = solution.shuffle();
        int[] reset = solution.reset();
        assertArrayEquals(nums, reset);
        Arrays.sort(nums);
        Arrays.sort(result);
        assertArrayEquals(nums, result);
    }

    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 2, 3 };
        test(nums);
        // Should use Chi-squared test
    }

    @Test
    public void testNothing() throws Exception {
        assertNotNull(new LC384ShuffleAnArray());
        Solution solution = new Solution(null);
        assertNull(solution.shuffle());
    }
}
