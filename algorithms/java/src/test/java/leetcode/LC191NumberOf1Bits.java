package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * #easy
 */
public class LC191NumberOf1Bits {
    public class Solution {
        public int hammingWeight(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                count += n & 1;
                n >>= 1;
            }
            return count;
        }
    }

    @Test
    public void test_11() throws Exception {
        assertEquals(3, new Solution().hammingWeight(11));
    }
}
