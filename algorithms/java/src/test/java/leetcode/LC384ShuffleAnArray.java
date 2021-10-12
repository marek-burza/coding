package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 * #medium
 */
public class LC384ShuffleAnArray {
    public class Solution {
        private final Random random;
        private final int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
            random = new Random();
        }

        public int[] reset() {
            return nums;
        }

        public int[] shuffle() {
            if (nums == null) return null;
            int[] result = nums.clone();
            for (int i = nums.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int exchange = result[i];
                result[i] = result[j];
                result[j] = exchange;
            }
            return result;
        }
    }

    private void test(int[] nums) {
        Solution solution = new Solution(nums.clone());
        int[] result = solution.shuffle();
        int[] reset = solution.reset();
        assertArrayEquals(nums, reset);
        Arrays.sort(nums);
        Arrays.sort(result);
        assertArrayEquals(nums, result);
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {1, 2, 3};
        test(nums);
    }

    @Test
    public void test_randomized() throws Exception {
        Random random = new Random();
        int length = random.nextInt(100000);
        int[] nums = random.ints(length, 0, length * 10).toArray();
        test(nums);
    }

    // Should use Chi-squared test
}
