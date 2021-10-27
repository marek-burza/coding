package leetcode.lc214_shortest_palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC214ShortestPalindromeTests {
    @Test
    public void testAacecaaa() throws Exception {
        assertEquals("aaacecaaa", new LC214ShortestPalindrome().shortestPalindrome("aacecaaa"));
    }

    @Test
    public void testAbcd() throws Exception {
        assertEquals("dcbabcd", new LC214ShortestPalindrome().shortestPalindrome("abcd"));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC214ShortestPalindrome().shortestPalindrome(null));
        assertEquals("", new LC214ShortestPalindrome().shortestPalindrome(""));
    }
}
