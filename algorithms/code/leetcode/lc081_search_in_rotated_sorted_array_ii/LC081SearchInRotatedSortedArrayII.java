package leetcode.lc081_search_in_rotated_sorted_array_ii;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * #medium
 */
public final class LC081SearchInRotatedSortedArrayII {
    public boolean search(final int[] nums, final int target) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                boolean ante = Arrays.binarySearch(nums, 0, i, target) >= 0;
                boolean post = Arrays.binarySearch(nums, i, nums.length, target) >= 0;
                return ante || post;
            }
        }
        return Arrays.binarySearch(nums, 0, nums.length, target) >= 0;
    }
}
