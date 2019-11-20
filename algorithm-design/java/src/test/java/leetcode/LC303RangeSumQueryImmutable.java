package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 */
public class LC303RangeSumQueryImmutable {
    public class NumArray {
        private int[] sums;

        public NumArray(int[] nums) {
            sums = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum = sums[i] = sum + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            if (0 < i) {
                sum = -sums[i - 1];
            }
            sum += sums[j];
            return sum;
        }
    }

    @Test
    public void test_0__2() throws Exception {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        assertEquals(1, new NumArray(nums).sumRange(0, 2));
    }

    @Test
    public void test_2__5() throws Exception {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        assertEquals(-1, new NumArray(nums).sumRange(2, 5));
    }

    @Test
    public void test_0__5() throws Exception {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        assertEquals(-3, new NumArray(nums).sumRange(0, 5));
    }
}
