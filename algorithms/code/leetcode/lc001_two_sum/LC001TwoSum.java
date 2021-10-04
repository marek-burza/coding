package leetcode.lc001_two_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public final class LC001TwoSum {
    public int[] twoSum(final int[] nums, final int target) {
        if (nums == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int expected = target - nums[i];
            Integer found = map.get(expected);
            if (found != null) {
                return new int[] { found, i };
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
