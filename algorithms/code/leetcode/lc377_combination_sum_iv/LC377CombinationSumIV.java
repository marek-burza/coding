package leetcode.lc377_combination_sum_iv;

/**
 * https://leetcode.com/problems/combination-sum-iv/ #medium
 * #dynamic-programming
 */
public final class LC377CombinationSumIV {
    public int combinationSum4(final int[] nums, final int target) {
        int[] cache = new int[target + 1];
        cache[0] = 1;
        for (int i = 0; i < target; i++) {
            if (cache[i] == 0) {
                continue;
            }
            for (int num : nums) {
                if (i + num <= target) {
                    cache[i + num] += cache[i];
                }
            }
        }
        return cache[target];
    }
}
