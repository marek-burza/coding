package leetcode.lc125_valid_palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome/ #easy
 */
public final class LC125ValidPalindrome {
    public boolean isPalindrome(final String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            if (!Character.isLetterOrDigit(a)) {
                i++;
                continue;
            }
            char b = s.charAt(j);
            if (!Character.isLetterOrDigit(b)) {
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
package leetcode.lc125_valid_palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC125ValidPalindromeTests {
    @Test
    public void testAManAPlanACanalPanama() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testRaceACar() throws Exception {
        assertFalse(new LC125ValidPalindrome().isPalindrome("race a car"));
    }

    @Test
    public void testAva() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome("Ava"));
    }

    @Test
    public void testBurger() throws Exception {
        assertFalse(new LC125ValidPalindrome().isPalindrome("burger"));
    }

    @Test
    public void testNothing() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome(null));
        assertTrue(new LC125ValidPalindrome().isPalindrome(""));
    }
}
