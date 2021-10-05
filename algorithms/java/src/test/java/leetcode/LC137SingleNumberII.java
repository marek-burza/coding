package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/single-number-ii/
 * #medium
 */
public class LC137SingleNumberII {
    public class Solution {
        public int singleNumber(int[] nums) {
            int[] counters = new int[32];
            for (int num : nums) {
                for (int i = 0; i < counters.length; i++, num >>= 1) {
                    counters[i] += num & 1;
                }
            }
            int result = 0;
            for (int i = 0, mask = 1; i < counters.length; i++, mask <<= 1) {
                if (counters[i] % 3 != 0) {
                    result |= mask;
                }
            }
            return result;
        }
    }

    @Test
    public void test_1112() throws Exception {
        int[] nums = {1, 1, 1, 2};
        assertEquals(2, new Solution().singleNumber(nums));
    }

    @Test
    public void test_4344533() throws Exception {
        int[] nums = {4, 3, 4, 4, 5, 3, 3};
        assertEquals(5, new Solution().singleNumber(nums));
    }
}
