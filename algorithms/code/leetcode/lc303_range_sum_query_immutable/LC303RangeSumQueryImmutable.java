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
