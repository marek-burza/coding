package leetcode;

/**
 * https://leetcode.com/problems/reverse-bits/
 */
public class LC190ReverseBits {
    public int reverseBits(int n) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r <<= 1;
            r |= n & 1;
            n >>= 1;
        }
        return r;
    }

    public static void main(String[] arguments) {
        // Should be 964176192
        System.out.println(new LC190ReverseBits().reverseBits(43261596));
    }
}
