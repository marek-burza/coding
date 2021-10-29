package leetcode.lc190_reverse_bits;

/**
 * https://leetcode.com/problems/reverse-bits/ #easy
 */
public final class LC190ReverseBits {
    public int reverseBits(final int nValue) {
        int n = nValue;
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r <<= 1;
            r |= n & 1;
            n >>= 1;
        }
        return r;
    }
}
