package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/ugly-number/
 * #easy
 */
public final class LC263UglyNumber {
    public final class Solution {
        public boolean isUgly(final int num) {
            if (num <= 0) return false;
            if (num == 1) return true;
            int original = num;
            while (num % 2 == 0) {
                num /= 2;
            }
            while (num % 3 == 0) {
                num /= 3;
            }
            while (num % 5 == 0) {
                num /= 5;
            }
            return num != original && num == 1;
        }
    }

    @Test
    public void test_minus() throws Exception {
        assertEquals(false, new Solution().isUgly(Integer.MIN_VALUE));
    }


    @Test
    public void test_0() throws Exception {
        assertEquals(false, new Solution().isUgly(0));
    }


    @Test
    public void test_1() throws Exception {
        assertEquals(true, new Solution().isUgly(1));
    }


    @Test
    public void test_2() throws Exception {
        assertEquals(true, new Solution().isUgly(2));
    }


    @Test
    public void test_3() throws Exception {
        assertEquals(true, new Solution().isUgly(3));
    }


    @Test
    public void test_7() throws Exception {
        assertEquals(false, new Solution().isUgly(7));
    }


    @Test
    public void test_11() throws Exception {
        assertEquals(false, new Solution().isUgly(11));
    }

    @Test
    public void test_14() throws Exception {
        assertEquals(false, new Solution().isUgly(14));
    }

    @Test
    public void test_16() throws Exception {
        assertEquals(true, new Solution().isUgly(16));
    }

    @Test
    public void test_27() throws Exception {
        assertEquals(true, new Solution().isUgly(27));
    }

    @Test
    public void test_937351770() throws Exception {
        assertEquals(false, new Solution().isUgly(937351770));
    }

    @Test
    public void test_905391974() throws Exception {
        assertEquals(false, new Solution().isUgly(905391974));
    }
}
