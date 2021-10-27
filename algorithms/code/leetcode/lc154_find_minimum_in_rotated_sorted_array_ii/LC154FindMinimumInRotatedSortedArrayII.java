package leetcode.lc154_find_minimum_in_rotated_sorted_array_ii;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/ #hard
 */
public final class LC154FindMinimumInRotatedSortedArrayII {
    public int findMin(final int[] nums) {
        int a = 0;
        int z = nums.length - 1;
        while (a < z) {
            int m = (a + z) >>> 1;
            if (nums[z] < nums[m]) {
                a = m + 1;
            } else if (nums[m] < nums[z]) {
                z = m;
            } else {
                z--;
            }
        }
        return nums[a];
    }
}
