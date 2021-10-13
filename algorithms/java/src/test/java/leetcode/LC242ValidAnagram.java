package leetcode.lc242_valid_anagram;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/valid-anagram/
 * #easy
 */
public final class LC242ValidAnagram {
    public final class Solution {
        public boolean isAnagram(final String s, final String t) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            if (sChars.length != tChars.length) {
                return false;
            }
            Arrays.sort(sChars);
            Arrays.sort(tChars);
            for (int i = 0; i < sChars.length; i++) {
                if (sChars[i] != tChars[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test_a_b() throws Exception {
        assertEquals(false, new Solution().isAnagram("a", "b"));
    }

    @Test
    public void test_anagram_nagaram() throws Exception {
        assertEquals(true, new Solution().isAnagram("anagram", "nagaram"));
    }

    @Test
    public void test_rat_car() throws Exception {
        assertEquals(false, new Solution().isAnagram("rat", "car"));
    }
}
