package leetcode.lc075_sort_colors;

/**
 * https://leetcode.com/problems/sort-colors/
 * #medium
 */
public final class LC075SortColors {
    public void sortColors(final int[] nums) {
        int[] counters = { 0, 0, 0 };
        for (int value : nums) {
            counters[value]++;
        }
        for (int i = 0, j = 0; i < counters.length; i++) {
            for (int k = 0; k < counters[i]; k++, j++) {
                nums[j] = i;
            }
        }
    }
}
