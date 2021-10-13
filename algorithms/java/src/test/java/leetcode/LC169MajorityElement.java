package leetcode.lc169_majority_element;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/majority-element/
 * #easy
 */
public final class LC169MajorityElement {
    public final class Solution {
        public int majorityElement(final int[] nums) {
            HashMap<Integer, Integer> frequencies = new HashMap<>();
            for (int value : nums) {
                Integer count = frequencies.get(value);
                if (count == null) {
                    count = 0;
                }
                frequencies.put(value, count + 1);
            }
            int result = Integer.MIN_VALUE;
            int count = Integer.MIN_VALUE;
            for (int value : frequencies.keySet()) {
                int other = frequencies.get(value);
                if (count <= other) {
                    result = value;
                    count = other;
                }
            }
            return result;
        }
    }

    @Test
    public void test_1_2_3_1_5_1_6_1() throws Exception {
        int[] nums = {1, 2, 3, 1, 5, 1, 6, 1};
        assertEquals(1, new Solution().majorityElement(nums));
    }
}
