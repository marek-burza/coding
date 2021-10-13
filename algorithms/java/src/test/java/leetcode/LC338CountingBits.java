package leetcode.lc338_counting_bits;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/counting-bits/
 * #easy
 */
public final class LC338CountingBits {
    public final class Solution {
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

    @Test
    public void test_2() throws Exception {
        assertArrayEquals(new int[]{0, 1, 1}, new Solution().countBits(2));
    }

    @Test
    public void test_5() throws Exception {
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, new Solution().countBits(5));
    }
}
