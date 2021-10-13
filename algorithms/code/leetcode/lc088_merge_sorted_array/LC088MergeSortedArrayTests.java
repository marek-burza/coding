package leetcode.lc088_merge_sorted_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC088MergeSortedArrayTests {
    @Test
    public void testExample1() throws Exception {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        int[] expected = { 1, 2, 2, 3, 5, 6 };
        new LC088MergeSortedArray().merge(nums1, 3, nums2, 3);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void testExample2() throws Exception {
        int[] nums1 = { 1 };
        int[] nums2 = {};
        int[] expected = { 1 };
        new LC088MergeSortedArray().merge(nums1, 1, nums2, 0);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void testExample3() throws Exception {
        int[] nums1 = { 0 };
        int[] nums2 = { 1 };
        int[] expected = { 1 };
        new LC088MergeSortedArray().merge(nums1, 0, nums2, 1);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void test13711000And4And4620And() throws Exception {
        int[] nums1 = { 1, 3, 7, 11, 0, 0, 0 };
        int[] nums2 = { 4, 6, 20 };
        int[] expected = { 1, 3, 4, 6, 7, 11, 20 };
        new LC088MergeSortedArray().merge(nums1, 4, nums2, 3);
        assertArrayEquals(expected, nums1);
    }
}
