package leetcode.lc334_increasing_triplet_subsequence;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * #medium
 */
public final class LC334IncreasingTripletSubsequence {
    public boolean increasingTriplet(final int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int[] minBefore = new int[nums.length];
        int[] maxAfter = new int[nums.length];
        minBefore[0] = nums[0];
        maxAfter[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            minBefore[i] = Math.min(minBefore[i - 1], nums[i - 1]);
            maxAfter[nums.length - 1 - i] = Math.max(maxAfter[nums.length - i], nums[nums.length - i]);
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (minBefore[i] < nums[i] && nums[i] < maxAfter[i]) {
                return true;
            }
        }
        return false;
    }
}
