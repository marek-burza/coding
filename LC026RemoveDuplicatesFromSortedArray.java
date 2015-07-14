package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class LC026RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int counter = 0;
        for (int i = 1; i < nums.length; i++) {
            int spot = counter;
            if (nums[i] == nums[i - 1 - spot]) {
                counter++;
            }
            nums[i - spot] = nums[i];
        }
        return nums.length - counter;
    }

    public static void main(String[] arguments) {
        LC026RemoveDuplicatesFromSortedArray solution = new LC026RemoveDuplicatesFromSortedArray();
        int[] nums1 = {1, 2, 2, 3, 4, 4, 7};
        System.out.println(solution.removeDuplicates(nums1));
        System.out.println(Arrays.toString(nums1));
    }
}
