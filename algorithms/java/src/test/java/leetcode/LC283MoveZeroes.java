package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/move-zeroes/
 * #easy
 */
public class LC283MoveZeroes {
    public class Solution {
        public void moveZeroes(int[] nums) {
            int target = 0;
            for (int index = 0; index < nums.length; index++) {
                nums[target] = nums[index];
                if (nums[index] != 0) {
                    target++;
                }
            }
            Arrays.fill(nums, target, nums.length, 0);
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        int[] expected = {1, 3, 12, 0, 0};
        assertArrayEquals(expected, nums);
    }
}
