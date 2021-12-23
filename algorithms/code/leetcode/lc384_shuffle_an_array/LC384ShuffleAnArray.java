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
