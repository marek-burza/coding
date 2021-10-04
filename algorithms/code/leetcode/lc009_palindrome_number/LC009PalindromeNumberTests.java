package leetcode.lc009_palindrome_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC009PalindromeNumberTests {
    @Test
    public void test213() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(false, solution.isPalindrome(213));
    }

    @Test
    public void test456() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(false, solution.isPalindrome(456));
    }

    @Test
    public void test454() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(true, solution.isPalindrome(454));
    }

    @Test
    public void test99() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(true, solution.isPalindrome(99));
    }

    @Test
    public void test1() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(true, solution.isPalindrome(1));
    }

    @Test
    public void test10() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertEquals(false, solution.isPalindrome(10));
    }

    @Test
    public void testMinus1() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertFalse(solution.isPalindrome(-1));
    }

    @Test
    public void test0() throws Exception {
        LC009PalindromeNumber solution;
        solution = new LC009PalindromeNumber();
        assertTrue(solution.isPalindrome(0));
    }
}
