package leetcode.lc033_search_in_rotated_sorted_array;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * #medium
 */
public final class LC033SearchInRotatedSortedArray {
    public int search(final int[] nums, final int target) {
        int a = 0;
        int z = nums.length - 1;
        while (a <= z) {
            int m = (a + z) >>> 1;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                if (target <= nums[z] || nums[a] < nums[m]) {
                    a = m + 1;
                } else {
                    z = m - 1;
                }
            } else {
                if (nums[a] <= target || nums[m] < nums[z]) {
                    z = m - 1;
                } else {
                    a = m + 1;
                }
            }
        }
        return -1;
    }
}
