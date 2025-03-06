package leetcode.lc198_house_robber;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/house-robber/
 * #medium
 */
public final class LC198HouseRobber {
    public int rob(final int[] nums, final int offset, final HashMap<Integer, Integer> maxed) {
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

    public int rob(final int[] nums) {
        return rob(nums, 0, new HashMap<Integer, Integer>());
    }
}
package leetcode.lc198_house_robber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC198HouseRobberTests {
    @Test
    public void test664843310() throws Exception {
        int[] nums = { 6, 6, 4, 8, 4, 3, 3, 10 };
        assertEquals(27, new LC198HouseRobber().rob(nums));
    }
}
