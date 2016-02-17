package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LC003LongestSubstringWithoutRepeatingCharacters {
    public class Solution {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> seen = new HashSet<>();
            int longest = 0;
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                Character found = s.charAt(i);
                while(count > 0 && seen.contains(found)) {
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

    @Test
    public void test_abcabcbb() throws Exception {
        assertEquals(3, new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void test_bbbbb() throws Exception {
        assertEquals(1, new Solution().lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    public void test_dvdf() throws Exception {
        assertEquals(3, new Solution().lengthOfLongestSubstring("dvdf"));
    }
}
