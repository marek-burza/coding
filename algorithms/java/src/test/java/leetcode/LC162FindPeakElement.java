package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-peak-element/
 * #medium
 */
public class LC162FindPeakElement {
    public class Solution {
        public int findPeakElement(int[] nums) {
            for (int i = 1; i <= nums.length; i++) {
                boolean postFalling;
                postFalling = i == nums.length || nums[i - 1] > nums[i];
                if (postFalling) {
                    return i - 1;
                }
            }
            return -1;
        }
    }

    @Test
    public void test_1_2_3_1() throws Exception {
        assertEquals(2, new Solution().findPeakElement(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void test_1_2_3_4() throws Exception {
        assertEquals(3, new Solution().findPeakElement(new int[]{1, 2, 3, 4}));
    }
}
