package leetcode.lc153_find_minimum_in_rotated_sorted_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC153FindMinimumInRotatedSortedArrayTests {
    @Test
    public void test0124567() throws Exception {
        int[] nums = { 0, 1, 2, 4, 5, 6, 7 };
        assertEquals(0, new LC153FindMinimumInRotatedSortedArray().findMin(nums));
    }

    @Test
    public void test4567012() throws Exception {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(0, new LC153FindMinimumInRotatedSortedArray().findMin(nums));
    }

    @Test
    public void test12() throws Exception {
        int[] nums = { 1, 2 };
        assertEquals(1, new LC153FindMinimumInRotatedSortedArray().findMin(nums));
    }

    @Test
    public void test21() throws Exception {
        int[] nums = { 2, 1 };
        assertEquals(1, new LC153FindMinimumInRotatedSortedArray().findMin(nums));
    }

    @Test
    public void test1() throws Exception {
        int[] nums = { 1 };
        assertEquals(1, new LC153FindMinimumInRotatedSortedArray().findMin(nums));
    }
}
