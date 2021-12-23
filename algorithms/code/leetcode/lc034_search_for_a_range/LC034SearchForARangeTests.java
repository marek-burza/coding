package leetcode.lc034_search_for_a_range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC034SearchForARangeTests {
    @Test
    public void testExample1() throws Exception {
        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int[] expected = { 3, 4 };
        assertArrayEquals(expected, new LC034SearchForARange().searchRange(nums, 8));
    }

    @Test
    public void testOther() throws Exception {
        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int[] expected = { -1, -1 };
        assertArrayEquals(expected, new LC034SearchForARange().searchRange(nums, 6));
    }

    @Test
    public void testAnother() throws Exception {
        int[] nums = { 2, 2 };
        int[] expected = { -1, -1 };
        assertArrayEquals(expected, new LC034SearchForARange().searchRange(nums, 3));
    }

    @Test
    public void testNothing() throws Exception {
        int[] expected = { -1, -1 };
        assertArrayEquals(expected, new LC034SearchForARange().searchRange(null, 3));
        assertArrayEquals(expected, new LC034SearchForARange().searchRange(new int[] {}, 3));
    }
}
