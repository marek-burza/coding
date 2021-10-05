package leetcode.lc009_palindrome_number;

/**
 * https://leetcode.com/problems/palindrome-number/
 * #easy
 */
public final class LC009PalindromeNumber {
    public boolean isPalindrome(final int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int power = (int) (Math.floor(Math.log10(x)));
        int m = (int) Math.pow(10, power);
        int current = x;
        for (int i = 0; i < Math.ceil((power + 1) / 2.0); i++) {
            current -= (current % 10) * m;
            if (current < 0) {
                return false;
            }
            current /= 10;
            m /= 100;
        }
        return current == 0;
    }
}
