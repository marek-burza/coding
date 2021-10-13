package leetcode.lc343_integer_break;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/integer-break/
 * #medium
 */
public final class LC343IntegerBreak {
    public final class Solution {
        public int integerBreak(final int n) {
            if (n == 2) return 1;
            if (n == 3) return 2;
            if (n == 5) return 6;
            int threes = n / 3;
            int rest = (n - 3 * (threes - 1));
            rest = rest == 5 ? 6 : rest;
            return (int) Math.pow(3, threes - 1) * rest;
            //int product = 1;
            //while(n > 4){
            //    product *= 3;
            //    n -= 3;
            //}
            //return product * n;
        }
    }

    @Test
    public void test_2() throws Exception {
        assertEquals(1, new Solution().integerBreak(2));
    }

    @Test
    public void test_3() throws Exception {
        assertEquals(2, new Solution().integerBreak(3));
    }

    @Test
    public void test_4() throws Exception {
        assertEquals(4, new Solution().integerBreak(4));
    }

    @Test
    public void test_5() throws Exception {
        assertEquals(6, new Solution().integerBreak(5));
    }

    @Test
    public void test_6() throws Exception {
        assertEquals(9, new Solution().integerBreak(6));
    }

    @Test
    public void test_7() throws Exception {
        assertEquals(12, new Solution().integerBreak(7));
    }

    @Test
    public void test_8() throws Exception {
        assertEquals(18, new Solution().integerBreak(8));
    }

    @Test
    public void test_9() throws Exception {
        assertEquals(27, new Solution().integerBreak(9));
    }

    @Test
    public void test_10() throws Exception {
        assertEquals(36, new Solution().integerBreak(10));
    }
}