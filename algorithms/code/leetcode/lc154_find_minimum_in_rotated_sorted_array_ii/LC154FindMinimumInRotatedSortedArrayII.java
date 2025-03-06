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
package leetcode.lc154_find_minimum_in_rotated_sorted_array_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC154FindMinimumInRotatedSortedArrayIITests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(0, new LC154FindMinimumInRotatedSortedArrayII().findMin(nums));
    }

    @Test
    public void testTrickier() throws Exception {
        int[] nums = { 1, 1, 0, 1, 1, 1, 1 };
        assertEquals(0, new LC154FindMinimumInRotatedSortedArrayII().findMin(nums));
    }
}
