package leetcode.lc080_remove_duplicates_from_sorted_array_ii;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * #medium
 */
public final class LC080RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(final int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }
}
