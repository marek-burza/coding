package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class LC001TwoSum {
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

    public static void main(String[] arguments) {
        LC001TwoSum solution = new LC001TwoSum();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
