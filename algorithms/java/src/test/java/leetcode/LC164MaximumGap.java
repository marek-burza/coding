package leetcode;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/maximum-gap/
 * #hard
 */
public class LC164MaximumGap {
    public class Solution {
        private int min(int[] nums) {
            int min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                }
            }
            return min;
        }

        private int max(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            return max;
        }

        public int maximumGap(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            int maxE = max(nums);
            int minE = min(nums);
            double len = (double) (maxE - minE) / (double) (n - 1);
            int[] maxA = new int[n];
            Arrays.fill(maxA, Integer.MIN_VALUE);
            int[] minA = new int[n];
            Arrays.fill(minA, Integer.MAX_VALUE);
            for (int num : nums) {
                int index = (int) ((num - minE) / len);
                maxA[index] = Math.max(maxA[index], num);
                minA[index] = Math.min(minA[index], num);
            }
            int gap = 0, prev = maxA[0];
            for (int i = 1; i < n; i++) {
                if (minA[i] == Integer.MAX_VALUE) {
                    continue;
                }
                gap = Math.max(gap, minA[i] - prev);
                prev = maxA[i];
            }
            return gap;
            // Pigeon hole principle
            // We keep the biggest and smallest pigeon fitting in the hole and that's enough to find the gap in linear way
        }
    }

    @Test
    public void test_33_2_100_70() throws Exception {
        int[] nums1 = {33, 2, 100, 70};
        assertEquals(37, new Solution().maximumGap(nums1));
    }
}
