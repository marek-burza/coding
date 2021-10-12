package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/add-digits/
 * #easy
 */
public final class LC258AddDigits {
    public final class Solution {
        public int addDigits(final int num) {
            while (num >= 10) {
                int sum = 0;
                while (num > 0) {
                    int digit = num % 10;
                    sum += digit;
                    num /= 10;
                }
                num = sum;
            }
            return num;
            //return (int) (num - 9 * Math.floor((num - 1) / 9));
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals(2, new Solution().addDigits(38));
    }
}
