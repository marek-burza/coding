package leetcode.lc026_remove_duplicates_from_sorted_array;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * #easy
 */
public final class LC026RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(final int[] nums) {
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
}
