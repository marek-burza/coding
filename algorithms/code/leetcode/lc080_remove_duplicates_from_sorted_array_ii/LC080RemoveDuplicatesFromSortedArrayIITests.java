package leetcode.lc080_remove_duplicates_from_sorted_array_ii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC080RemoveDuplicatesFromSortedArrayIITests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int[] expected = { 1, 1, 2, 2, 3 };
        assertEquals(5, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test111133() throws Exception {
        int[] nums = { 1, 1, 1, 1, 3, 3 };
        int[] expected = { 1, 1, 3, 3 };
        assertEquals(4, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test11() throws Exception {
        int[] nums = { 1, 1 };
        int[] expected = { 1, 1 };
        assertEquals(2, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test122() throws Exception {
        int[] nums = { 1, 2, 2 };
        int[] expected = { 1, 2, 2 };
        assertEquals(3, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }
}
