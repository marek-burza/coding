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
