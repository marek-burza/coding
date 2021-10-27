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
