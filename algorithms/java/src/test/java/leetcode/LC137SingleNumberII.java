package leetcode.lc137_single_number_i_i;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/single-number-ii/
 * #medium
 */
public final class LC137SingleNumberII {
    public final class Solution {
        public int singleNumber(final int[] nums) {
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
