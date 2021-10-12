package leetcode.lc035_search_insert_position;

/**
 * https://leetcode.com/problems/search-insert-position/ #easy
 */
public final class LC035SearchInsertPosition {
    public int searchInsert(final int[] nums, final int target) {
        int a = 0;
        int z = nums.length;
        while (a != z) {
            int m = (a + z) >>> 1;
            if (nums[m] < target) {
                a = m + 1;
            } else {
                z = m;
            }
        }
        return z;
    }
}
