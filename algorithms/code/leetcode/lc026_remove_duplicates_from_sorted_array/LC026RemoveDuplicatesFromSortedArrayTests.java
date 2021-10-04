package leetcode.lc026_remove_duplicates_from_sorted_array;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC026RemoveDuplicatesFromSortedArrayTests {
    @Test
    public void test1223447() throws Exception {
        LC026RemoveDuplicatesFromSortedArray solution;
        solution = new LC026RemoveDuplicatesFromSortedArray();
        int[] nums1 = {1, 2, 2, 3, 4, 4, 7};
        int length = solution.removeDuplicates(nums1);
        assertEquals(5, length);
        int[] expected = {1, 2, 3, 4, 7};
        assertArrayEquals(expected, Arrays.copyOfRange(nums1, 0, length));
    }
}
