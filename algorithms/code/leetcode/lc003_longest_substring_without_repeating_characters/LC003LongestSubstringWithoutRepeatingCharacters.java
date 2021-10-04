package leetcode.lc003_longest_substring_without_repeating_characters;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public final  class LC003LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(final String s) {
        Set<Character> seen = new HashSet<>();
        int longest = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            Character found = s.charAt(i);
            while (count > 0 && seen.contains(found)) {
                seen.remove(s.charAt(i - count));
                count--;
            }
            count++;
            seen.add(found);
            longest = Math.max(longest, count);
        }
        return longest;
    }
}
