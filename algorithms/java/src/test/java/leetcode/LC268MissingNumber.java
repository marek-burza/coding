package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/missing-number/
 * #easy
 */
public final class LC268MissingNumber {
    public final class Solution {
        public int missingNumber(final int[] nums) {
            long expected = nums.length * (nums.length + 1) / 2;
            long sum = 0;
            for (int value : nums) {
                sum += value;
            }
            return (int) (expected - sum);
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals(2, new Solution().missingNumber(new int[]{0, 1, 3}));
    }
}
