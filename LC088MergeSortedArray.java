package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 */
public class LC088MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[i] = nums1[m];
                m--;
            } else {
                nums1[i] = nums2[n];
                n--;
            }
            i--;
        }
        while (n >= 0) {
            nums1[i] = nums2[n];
            n--;
            i--;
        }
    }

    public static void main(String[] arguments) {
        int[] nums1 = {1, 3, 7, 11, 0, 0, 0};
        int[] nums2 = {4, 6, 20};
        LC088MergeSortedArray solution = new LC088MergeSortedArray();
        solution.merge(nums1, 4, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
