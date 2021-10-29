package leetcode.lc242_valid_anagram;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/ #easy
 */
public final class LC242ValidAnagram {
    public boolean isAnagram(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.compare(sChars, tChars) == 0;
    }
}
