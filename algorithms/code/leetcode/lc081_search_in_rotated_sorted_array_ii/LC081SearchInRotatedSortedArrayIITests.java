package leetcode.lc081_search_in_rotated_sorted_array_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC081SearchInRotatedSortedArrayIITests {
    @Test
    public void testSimpleExample() {
        int[] nums = { 4, 5, 6, 6, 7, 0, 1, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 1));
    }

    @Test
    public void testTrickyExample() {
        int[] nums = { 1, 1, 3, 1, 1, 1, 1, 1 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 2));
    }

    @Test
    public void testOneAndOne() {
        int[] nums = { 1 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 1));
    }

    @Test
    public void testOneAndZero() {
        int[] nums = { 1 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 0));
    }

    @Test
    public void test2560012And3() {
        int[] nums = { 2, 5, 6, 0, 0, 1, 2 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 3));
    }

    @Test
    public void test2560012And0() {
        int[] nums = { 2, 5, 6, 0, 0, 1, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 0));
    }

    @Test
    public void test2223222And3() {
        int[] nums = { 2, 2, 2, 3, 2, 2, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 3));
    }
}
