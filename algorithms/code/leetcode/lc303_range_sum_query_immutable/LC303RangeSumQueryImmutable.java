package leetcode.lc303_range_sum_query_immutable;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/ #easy
 */
public final class LC303RangeSumQueryImmutable {
    public static final class NumArray {
        private int[] sums;

        NumArray(final int[] nums) {
            sums = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sums[i] = sum;
            }
        }

        public int sumRange(final int i, final int j) {
            int sum = 0;
            if (0 < i) {
                sum = -sums[i - 1];
            }
            sum += sums[j];
            return sum;
        }
    }
}
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
