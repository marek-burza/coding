package leetcode.lc058_length_of_last_word;

/**
 * https://leetcode.com/problems/length-of-last-word/ #easy
 */
public final class LC058LengthOfLastWord {
    public int lengthOfLastWord(final String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        while (n > 0 && s.charAt(n - 1) == ' ') {
            n--;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                return n - i - 1;
            }
        }
        return n;
    }
}
