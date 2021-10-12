package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/count-numbers-with-unique-digits/
 * #medium
 */
public final class LC357CountNumbersWithUniqueDigits {
    public final class Solution {
        private int count(final String prefix, final int n) {
            if (prefix.length() == n) {
                return 1;
            }
            int sum = 1;
            String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            int first = prefix.length() == 0 ? 1 : 0;
            for (int i = first; i < digits.length; i++) {
                if (!prefix.contains(digits[i])) {
                    sum += count(prefix + digits[i], n);
                }
            }
            return sum;
        }

        public int countNumbersWithUniqueDigits(final int n) {
            return count("", n);
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals(91, new Solution().countNumbersWithUniqueDigits(2));
    }

    @Test
    public void test_0() throws Exception {
        assertEquals(1, new Solution().countNumbersWithUniqueDigits(0));
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(10, new Solution().countNumbersWithUniqueDigits(1));
    }
}
