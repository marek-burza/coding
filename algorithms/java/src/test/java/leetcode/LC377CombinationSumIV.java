package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * #medium
 */
public class LC377CombinationSumIV {
    public class Solution {
        public int combinationSum4(int[] nums, int target, Map<Integer, Integer> cache) {
            Integer cached = cache.get(target);
            if (cached != null) {
                return cached;
            }
            int sum = 0;
            for (int value : nums) {
                int candidate = target - value;
                if (candidate > 0) {
                    sum += combinationSum4(nums, candidate, cache);
                }
                if (candidate == 0) {
                    sum++;
                }
                if (candidate < 0) {
                    break;
                }
            }
            cache.put(target, sum);
            return sum;
        }

        public int combinationSum4(int[] nums, int target) {
            Arrays.parallelSort(nums);
            return combinationSum4(nums, target, new HashMap<>());
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {1, 2, 3};
        assertEquals(7, new Solution().combinationSum4(nums, 4));
    }

    @Test
    public void test_longer_example() throws Exception {
        int[] nums = {4, 2, 1};
        assertEquals(39882198, new Solution().combinationSum4(nums, 32));
    }
}
