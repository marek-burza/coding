package leetcode.lc300_longest_increasing_subsequence;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * #medium
 */
public final class LC300LongestIncreasingSubsequence {
    public int lengthOfLIS(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int value : lis) {
            max = Math.max(max, value);
        }
        return max;
    }
}
package leetcode.lc300_longest_increasing_subsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC300LongestIncreasingSubsequenceTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        assertEquals(4, new LC300LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC300LongestIncreasingSubsequence().lengthOfLIS(null));
        assertEquals(0, new LC300LongestIncreasingSubsequence().lengthOfLIS(new int[] {}));
    }
}
