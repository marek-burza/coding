package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class LC008StringToIntegerAToI {
    public class Solution {
        public int myAtoi(String str) {
            long result = 0;
            long sign = 0;
            boolean white = true, any = false;
            for (char digit : str.toCharArray()) {
                if (white) {
                    if (Character.isWhitespace(digit)) {
                        continue;
                    } else {
                        white = false;
                    }
                }
                if (Character.isDigit(digit)) {
                    if (sign == 0) {
                        sign = 1;
                    }
                    result *= 10;
                    result += digit - '0';
                } else {
                    switch (digit) {
                        case '-':
                            if (sign == 0) {
                                sign = -1;
                                continue;
                            } else {
                                return 0;
                            }
                        case '+':
                            if (sign == 0) {
                                sign = 1;
                                continue;
                            } else {
                                return 0;
                            }
                    }
                    if (any) {
                        break;
                    } else {
                        return 0;
                    }
                }
                any = true;
                if (sign * result >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign * result <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            return (int) (sign * result);
        }
    }

    @Test
    public void test_MinusPlus3241() throws Exception {
        assertEquals(0, new Solution().myAtoi("-+3241"));
    }

    @Test
    public void test_Minus3241() throws Exception {
        assertEquals(-3241, new Solution().myAtoi("-3241"));
    }

    @Test
    public void test_SpaceMinusPlus3241A() throws Exception {
        assertEquals(-3241, new Solution().myAtoi(" -3241a"));
    }

    @Test
    public void test_9223372036854775809() throws Exception {
        assertEquals(Integer.MAX_VALUE, new Solution().myAtoi("9223372036854775809"));
    }
}
