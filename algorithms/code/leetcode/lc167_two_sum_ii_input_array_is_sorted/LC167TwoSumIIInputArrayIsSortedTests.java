package leetcode.lc167_two_sum_ii_input_array_is_sorted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC167TwoSumIIInputArrayIsSortedTests {
    @Test
    public void testExample() throws Exception {
        assertArrayEquals(new int[] { 1, 2 },
                new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 2, 7, 11, 15 }, 9));
    }

    @Test
    public void testOtherExample() throws Exception {
        assertArrayEquals(new int[] { 0, 0 },
                new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 1, 5, 6, 9 }, 9));
    }

    @Test
    public void testNothing() throws Exception {
        assertArrayEquals(new int[] { 0, 0 }, new LC167TwoSumIIInputArrayIsSorted().twoSum(null, 0));
        assertArrayEquals(new int[] { 0, 0 }, new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 0 }, 0));
    }
}
