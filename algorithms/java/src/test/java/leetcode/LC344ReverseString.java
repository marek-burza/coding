package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-string/
 * #easy
 */
public final class LC344ReverseString {
    public final class Solution {
        public String reverseString(final String s) {
            char[] reversed = s.toCharArray();
            for (int i = 0; i < Math.round(reversed.length / 2.0); i++) {
                char exchange = reversed[i];
                reversed[i] = reversed[reversed.length - 1 - i];
                reversed[reversed.length - 1 - i] = exchange;
            }
            return new String(reversed);
        }
    }

    @Test
    public void test_example() throws Exception {
        assertEquals("olleh", new Solution().reverseString("hello"));
    }
}
