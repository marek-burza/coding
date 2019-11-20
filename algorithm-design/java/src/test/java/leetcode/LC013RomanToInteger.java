package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/roman-to-integer/
 */
public class LC013RomanToInteger {
    public class Solution {
        public int romanToInt(String s) {
            if (s == null) return 0;
            int result = 0;
            int previous = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                int current = 0;
                switch (s.charAt(i)) {
                    case 'I':
                        current = 1;
                        break;
                    case 'V':
                        current = 5;
                        break;
                    case 'X':
                        current = 10;
                        break;
                    case 'L':
                        current = 50;
                        break;
                    case 'C':
                        current = 100;
                        break;
                    case 'D':
                        current = 500;
                        break;
                    case 'M':
                        current = 1000;
                        break;
                }
                current = current < previous ? -current : current;
                result += current;
                previous = current;
            }
            return result;
        }
    }

    @Test
    public void test_MCMLIV() throws Exception {
        assertEquals(1954, new Solution().romanToInt("MCMLIV"));
    }
}
