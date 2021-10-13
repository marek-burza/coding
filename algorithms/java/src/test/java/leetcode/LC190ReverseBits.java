package leetcode.lc190_reverse_bits;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-bits/
 * #easy
 */
public final class LC190ReverseBits {
    public final class Solution {
        public int reverseBits(final int n) {
            int r = 0;
            for (int i = 0; i < 32; i++) {
                r <<= 1;
                r |= n & 1;
                n >>= 1;
            }
            return r;
        }
    }

    @Test
    public void test_43261596() throws Exception {
        assertEquals(964176192, new Solution().reverseBits(43261596));
    }
}
