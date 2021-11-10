package leetcode.lc338_counting_bits;

/**
 * https://leetcode.com/problems/counting-bits/ #easy
 */
public final class LC338CountingBits {
    public int[] countBits(final int num) {
        int[] result = new int[num + 1];
        int threshold = 1;
        for (int i = 0; i < result.length; i++) {
            if ((threshold << 1) <= i) {
                threshold <<= 1;
            }
            if (i == 0) {
                result[0] = 0;
            } else {
                result[i] = 1 + result[i - threshold];
            }
        }
        return result;
    }
}
