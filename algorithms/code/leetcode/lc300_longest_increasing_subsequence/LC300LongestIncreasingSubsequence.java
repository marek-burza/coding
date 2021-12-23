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
