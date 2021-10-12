package leetcode.lc053_maximum_subarray;

/**
 * https://leetcode.com/problems/maximum-subarray/ #easy
 */
public final class LC053MaximumSubarray {
    public int maxSubArray(final int[] nums) {
        int sum = 0;
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = sum < min ? sum : min;
            sum += nums[i];
            int delta = sum - min;
            max = delta > max ? delta : max;
        }
        return max;
    }
}
