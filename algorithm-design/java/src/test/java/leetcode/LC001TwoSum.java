package leetcode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class LC001TwoSum {
    public class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums == null) {
                return null;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int expected = target - nums[i];
                Integer found = map.get(expected);
                if (found != null) {
                    return new int[]{found + 1, i + 1};
                } else {
                    map.put(nums[i], i);
                }
            }
            return null;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {2, 7, 11, 15};
        int[] expected = {1, 2};
        assertArrayEquals(expected, new Solution().twoSum(nums, 9));
    }
}
