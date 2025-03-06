package leetcode.lc384_shuffle_an_array;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 * #medium
 */
public final class LC384ShuffleAnArray {
    public static class Solution {
        private static final Random RANDOM = new SecureRandom();
        private final int[] nums;

        Solution(final int[] numsValue) {
            nums = numsValue;
        }

        public final int[] reset() {
            return Arrays.copyOf(nums, nums.length);
        }

        public final int[] shuffle() {
            if (nums == null) {
                return null;
            }
            int[] result = nums.clone();
            for (int i = nums.length - 1; i > 0; i--) {
                int j = RANDOM.nextInt(i + 1);
                int exchange = result[i];
                result[i] = result[j];
                result[j] = exchange;
            }
            return result;
        }
    }
}
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
