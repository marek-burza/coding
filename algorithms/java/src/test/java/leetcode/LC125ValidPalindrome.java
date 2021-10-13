package leetcode.lc125_valid_palindrome;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * #easy
 */
public final class LC125ValidPalindrome {
    public final class Solution {
        public boolean isPalindrome(final String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            int i = 0;
            int j = s.length() - 1;
            while (i <= j) {
                char a = s.charAt(i);
                if (!(Character.isAlphabetic(a) || Character.isDigit(a))) {
                    i++;
                    continue;
                }
                char b = s.charAt(j);
                if (!(Character.isAlphabetic(b) || Character.isDigit(b))) {
                    j--;
                    continue;
                }
                if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                    return false;
                } else {
                    i++;
                    j--;
                }
            }
            return true;
        }
    }

    @Test
    public void test_a_man_a_plan_a_canal_Panama() throws Exception {
        assertEquals(true, new Solution().isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void test_race_a_car() throws Exception {
        assertEquals(false, new Solution().isPalindrome("race a car"));
    }

    @Test
    public void test_ava() throws Exception {
        assertEquals(true, new Solution().isPalindrome("Ava"));
    }

    @Test
    public void test_burger() throws Exception {
        assertEquals(false, new Solution().isPalindrome("burger"));
    }
}
