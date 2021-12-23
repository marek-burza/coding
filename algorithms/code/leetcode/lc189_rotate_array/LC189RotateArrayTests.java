package leetcode.lc189_rotate_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC189RotateArrayTests {
    @Test
    public void test1234567And3() throws Exception {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        new LC189RotateArray().rotate(nums, 3);
        int[] expected = { 5, 6, 7, 1, 2, 3, 4 };
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test123456And2() throws Exception {
        int[] nums = { 1, 2, 3, 4, 5, 6 };
        new LC189RotateArray().rotate(nums, 2);
        int[] expected = { 5, 6, 1, 2, 3, 4 };
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test1And2() throws Exception {
        int[] nums = { 1 };
        new LC189RotateArray().rotate(nums, 1);
        int[] expected = { 1 };
        assertArrayEquals(expected, nums);
    }
}
