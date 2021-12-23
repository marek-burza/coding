package leetcode.lc128_longest_consecutive_sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC128LongestConsecutiveSequenceTests {
    @Test
    public void test100And4And200And1And3And2() throws Exception {
        int[] nums1 = { 100, 4, 200, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }

    @Test
    public void testLonger() throws Exception {
        int[] nums2 = { 4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2,
                -9, 1 };
        assertEquals(8, new LC128LongestConsecutiveSequence().longestConsecutive(nums2));
    }

    @Test
    public void testMax() throws Exception {
        int[] nums1 = { 100, 4, Integer.MAX_VALUE, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }

    @Test
    public void testMin() throws Exception {
        int[] nums1 = { 100, 4, Integer.MIN_VALUE, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }
}
