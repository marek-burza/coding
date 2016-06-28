package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/reverse-bits/
 */
public class LC190ReverseBits {
    public class Solution {
        public int reverseBits(int n) {
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
