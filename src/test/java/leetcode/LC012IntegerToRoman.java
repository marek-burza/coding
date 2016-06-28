package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/integer-to-roman/
 */
public class LC012IntegerToRoman {
    public class Solution {
        private String[] digits = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        private int[] weights = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        public String intToRoman(int num) {
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < digits.length; i++) {
                int multiple = num / weights[i];
                for (int j = 0; j < multiple; j++) {
                    roman.append(digits[i]);
                }
                num -= multiple * weights[i];
            }
            return roman.toString();
        }
    }

    @Test
    public void test_1234() throws Exception {
        assertEquals("MCCXXXIV", new Solution().intToRoman(1234));
    }

    @Test
    public void test_9() throws Exception {
        assertEquals("IX", new Solution().intToRoman(9));
    }
}
