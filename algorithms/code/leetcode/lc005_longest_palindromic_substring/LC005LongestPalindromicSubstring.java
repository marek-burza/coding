package leetcode.lc005_longest_palindromic_substring;

// import java.util.HashMap;
// import java.util.Map;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * #medium
 */
public final class LC005LongestPalindromicSubstring {
    public String longestPalindrome(final String s) {
        String longest = "";
        int index = 0;
        while (index < s.length()) {
            // Find head & tail
            int head = index;
            int tail = index;
            while (tail + 1 < s.length()) {
                if (s.charAt(head) == s.charAt(tail + 1)) {
                    tail++;
                } else {
                    break;
                }
            }
            index += 1 + tail - head;
            // Expand
            while (0 <= head - 1 && tail + 1 < s.length() && s.charAt(head - 1) == s.charAt(tail + 1)) {
                head--;
                tail++;
            }
            // Check
            if (longest.length() < 1 + tail - head) {
                longest = s.substring(head, 1 + tail);
            }
        }
        return longest;
    }
}
