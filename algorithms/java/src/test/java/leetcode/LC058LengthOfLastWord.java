package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/length-of-last-word/
 * #easy
 */
public class LC058LengthOfLastWord {
    public final class Solution {
        public int lengthOfLastWord(String s) {
            if (s == null || s.length() == 0) return 0;
            int n = s.length();
            while (n > 0 && s.charAt(n - 1) == ' ') {
                n--;
            }
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == ' ') return n - i - 1;
            }
            return n;
        }
    }

    @Test
    public void test_Hello_World() throws Exception {
        assertEquals(5, new Solution().lengthOfLastWord("Hello World"));
    }
}
