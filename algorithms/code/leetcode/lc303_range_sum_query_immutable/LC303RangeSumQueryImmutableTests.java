package leetcode.lc303_range_sum_query_immutable;

import org.junit.jupiter.api.Test;

import leetcode.lc303_range_sum_query_immutable.LC303RangeSumQueryImmutable.NumArray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class LC303RangeSumQueryImmutableTests {
    @Test
    public void test0And2() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(1, new NumArray(nums).sumRange(0, 2));
    }

    @Test
    public void test2And5() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(-1, new NumArray(nums).sumRange(2, 5));
    }

    @Test
    public void test0And5() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(-3, new NumArray(nums).sumRange(0, 5));
    }

    @Test
    public void testCoverage() throws Exception {
        assertNotNull(new LC303RangeSumQueryImmutable());
    }
}
