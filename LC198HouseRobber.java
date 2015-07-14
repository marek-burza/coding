package leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class LC198HouseRobber {
    public int rob(int[] nums, int offset, HashMap<Integer, Integer> maxed) {
        if (nums.length <= offset) {
            return 0;
        }
        if (maxed.containsKey(offset)) {
            return maxed.get(offset);
        } else {
            int result = nums[offset] + rob(nums, offset + 2, maxed);
            if (offset + 1 < nums.length) {
                int other = nums[offset + 1] + rob(nums, offset + 3, maxed);
                result = Math.max(result, other);
            }
            maxed.put(offset, result);
            return (result);
        }
    }

    public int rob(int[] nums) {
        return rob(nums, 0, new HashMap<Integer, Integer>());
    }

    public static void main(String[] arguments) {
        int[] nums = {6, 6, 4, 8, 4, 3, 3, 10};
        System.out.println(new LC198HouseRobber().rob(nums));
    }
}
