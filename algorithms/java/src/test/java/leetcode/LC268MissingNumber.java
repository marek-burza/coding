package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/missing-number/
 * #easy
 */
public class LC268MissingNumber {
    public class Solution {
        public int missingNumber(int[] nums) {
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
