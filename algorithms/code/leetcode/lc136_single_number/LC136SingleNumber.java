package leetcode.lc136_single_number;

/**
 * https://leetcode.com/problems/single-number/ #easy
 */
public final class LC136SingleNumber {
    public int singleNumber(final int[] nums) {
        int result = 0;
        for (int value : nums) {
            result ^= value;
        }
        return result;
    }
}
