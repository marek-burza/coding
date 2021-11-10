package leetcode.lc268_missing_number;

/**
 * https://leetcode.com/problems/missing-number/ #easy
 */
public final class LC268MissingNumber {
    public int missingNumber(final int[] nums) {
        long expected = nums.length * (nums.length + 1) / 2;
        long sum = 0;
        for (int value : nums) {
            sum += value;
        }
        return (int) (expected - sum);
    }
}
