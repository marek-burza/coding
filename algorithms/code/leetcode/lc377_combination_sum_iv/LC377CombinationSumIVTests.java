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
