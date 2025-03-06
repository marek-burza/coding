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
package leetcode.lc377_combination_sum_iv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/combination-sum-iv/ #medium
 */
public class LC377CombinationSumIVTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 2, 3 };
        assertEquals(7, new LC377CombinationSumIV().combinationSum4(nums, 4));
    }

    @Test
    public void testLongerExample() throws Exception {
        int[] nums = { 4, 2, 1 };
        assertEquals(39882198, new LC377CombinationSumIV().combinationSum4(nums, 32));
    }

    @Test
    public void testWithGaps() throws Exception {
        int[] nums = { 3, 2 };
        assertEquals(28, new LC377CombinationSumIV().combinationSum4(nums, 15));
    }
}
