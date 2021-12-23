package leetcode.lc300_longest_increasing_subsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC300LongestIncreasingSubsequenceTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        assertEquals(4, new LC300LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC300LongestIncreasingSubsequence().lengthOfLIS(null));
        assertEquals(0, new LC300LongestIncreasingSubsequence().lengthOfLIS(new int[] {}));
    }
}
