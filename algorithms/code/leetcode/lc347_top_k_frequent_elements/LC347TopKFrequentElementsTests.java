package leetcode.lc347_top_k_frequent_elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC347TopKFrequentElementsTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int[] expected = { 1, 2 };
        assertArrayEquals(expected, new LC347TopKFrequentElements().topKFrequent(nums, 2));
    }
}
