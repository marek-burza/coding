package leetcode.lc260_single_number_iii;

/**
 * https://leetcode.com/problems/single-number-iii/
 * #medium
 */
public final class LC260SingleNumberIII {
    public int[] singleNumber(final int[] nums) {
        int xor = 0;
        for (int value : nums) {
            xor ^= value;
        }
        int mask = xor & (~(xor - 1));
        int[] values = { 0, 0 };
        for (int value : nums) {
            if ((value & mask) == 0) {
                values[0] ^= value;
            } else {
                values[1] ^= value;
            }
        }
        return values;
    }
}
