package leetcode.lc033_search_in_rotated_sorted_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC033SearchInRotatedSortedArrayTests {
    @Test
    public void testExample() {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(5, new LC033SearchInRotatedSortedArray().search(nums, 1));
    }

    @Test
    public void testSingle() {
        int[] nums = { 1 };
        assertEquals(0, new LC033SearchInRotatedSortedArray().search(nums, 1));
    }

    @Test
    public void testOther() {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(-1, new LC033SearchInRotatedSortedArray().search(nums, 3));
    }

    @Test
    public void testAnother() {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(4, new LC033SearchInRotatedSortedArray().search(nums, 0));
    }

    @Test
    public void test13And3() {
        int[] nums = { 1, 3 };
        assertEquals(1, new LC033SearchInRotatedSortedArray().search(nums, 3));
    }

    @Test
    public void test351And3() {
        int[] nums = { 3, 5, 1 };
        assertEquals(0, new LC033SearchInRotatedSortedArray().search(nums, 3));
    }

    @Test
    public void test51234And1() {
        int[] nums = { 5, 1, 2, 3, 4 };
        assertEquals(1, new LC033SearchInRotatedSortedArray().search(nums, 1));
    }
}
