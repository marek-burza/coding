package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-array/
 */
public class LC189RotateArray {
    private void reverse(int[] nums, int a, int b) {
        while (a < b) {
            int swap = nums[a];
            nums[a] = nums[b];
            nums[b] = swap;
            a++;
            b--;
        }
    }

    public void rotate(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void main(String[] arguments) {
        int[] nums0 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(nums0));
        new LC189RotateArray().rotate(nums0, 3);
        System.out.println(Arrays.toString(nums0));
        int[] nums1 = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(nums1));
        new LC189RotateArray().rotate(nums1, 2);
        System.out.println(Arrays.toString(nums1));
        int[] nums2 = {1};
        System.out.println(Arrays.toString(nums2));
        new LC189RotateArray().rotate(nums2, 1);
        System.out.println(Arrays.toString(nums2));
    }
}
