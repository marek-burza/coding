package leetcode.lc376_wiggle_subsequence;

/**
 * https://leetcode.com/problems/wiggle-subsequence/
 * #medium
 */
public final class LC376WiggleSubsequence {
    public int wiggleMaxLength(final int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        double then = Math.signum(nums[1] - nums[0]);
        int count = then != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            double now = Math.signum(nums[i] - nums[i - 1]);
            if (now != 0) {
                if (now != then) {
                    count++;
                }
                then = now;
            }
        }
        return count;
    }
}
