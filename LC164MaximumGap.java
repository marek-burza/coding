package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-gap/
 */
public class LC164MaximumGap {
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
        for (int i = 0; i < n; i++) {
            int index = (int) ((nums[i] - minE) / len);
            maxA[index] = Math.max(maxA[index], nums[i]);
            minA[index] = Math.min(minA[index], nums[i]);
        }
        System.out.println(Arrays.toString(maxA));
        System.out.println(Arrays.toString(minA));
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

    public static void main(String[] arguments) {
        LC164MaximumGap solution = new LC164MaximumGap();
        int[] nums1 = {33, 2, 100, 70};
        System.out.println(solution.maximumGap(nums1));
    }
}
