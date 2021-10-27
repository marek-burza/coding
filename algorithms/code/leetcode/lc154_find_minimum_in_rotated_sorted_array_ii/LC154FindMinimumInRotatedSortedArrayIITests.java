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
