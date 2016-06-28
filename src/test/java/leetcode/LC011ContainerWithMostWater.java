package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
public class LC011ContainerWithMostWater {
    public class Solution {
        public int maxArea(int[] height) {
            int max = 0;
            int low = 0;
            int high = height.length - 1;
            while (low < high) {
                int top = Math.min(height[low], height[high]);
                max = Math.max(max, top * (high - low));
                if (height[low] <= height[high]) {
                    low++;
                } else {
                    high--;
                }
            }
            return max;
        }
    }

    @Test
    public void test_1_2_1() throws Exception {
        int[] test = {1, 2, 1};
        assertEquals(2, new Solution().maxArea(test));
    }

    @Test
    public void test_1_3_5_2() throws Exception {
        int[] test = {1, 3, 5, 2};
        assertEquals(4, new Solution().maxArea(test));
    }

    @Test
    public void test_oversized() throws Exception {
        int[] test = new int[15000];
        for (int i = 0; i < 15000; i++) {
            test[i] = i + 1;
        }
        assertEquals(56250000, new Solution().maxArea(test));
    }

    @Test
    public void test_huh() throws Exception {
        int[] test = {1, 2, 1, 15, 15, 1, 2, 1};
        assertEquals(15, new Solution().maxArea(test));
    }
}
