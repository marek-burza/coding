package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * #medium
 */
public final class LC080RemoveDuplicatesFromSortedArrayII {
    public final class Solution {
        public int removeDuplicates(final int[] nums) {
            int i = 0;
            for (int n : nums)
                if (i < 2 || n > nums[i - 2])
                    nums[i++] = n;
            return i;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] expected = {1, 1, 2, 2, 3};
        assertEquals(5, new Solution().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test_1_1_1_1_3_3() throws Exception {
        int[] nums = {1, 1, 1, 1, 3, 3};
        int[] expected = {1, 1, 3, 3};
        assertEquals(4, new Solution().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test_1_1() throws Exception {
        int[] nums = {1, 1};
        int[] expected = {1, 1};
        assertEquals(2, new Solution().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }


    @Test
    public void test_1_2_2() throws Exception {
        int[] nums = {1, 2, 2};
        int[] expected = {1, 2, 2};
        assertEquals(3, new Solution().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }
}
