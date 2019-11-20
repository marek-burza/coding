package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/palindrome-number/
 */
public class LC009PalindromeNumber {
    public class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            if (x == 0) {
                return true;
            }
            int power = (int) (Math.floor(Math.log10(x)));
            int m = (int) Math.pow(10, power);
            for (int i = 0; i < Math.ceil((power + 1) / 2.0); i++) {
                x -= (x % 10) * m;
                if (x < 0) {
                    return false;
                }
                x /= 10;
                m /= 100;
            }
            return x == 0;
        }
    }

    @Test
    public void test_213() throws Exception {
        assertEquals(false, new Solution().isPalindrome(213));
    }

    @Test
    public void test_456() throws Exception {
        assertEquals(false, new Solution().isPalindrome(456));
    }

    @Test
    public void test_454() throws Exception {
        assertEquals(true, new Solution().isPalindrome(454));
    }

    @Test
    public void test_99() throws Exception {
        assertEquals(true, new Solution().isPalindrome(99));
    }

    @Test
    public void test_1() throws Exception {
        assertEquals(true, new Solution().isPalindrome(1));
    }

    @Test
    public void test_10() throws Exception {
        assertEquals(false, new Solution().isPalindrome(10));
    }
}
