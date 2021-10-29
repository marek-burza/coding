package leetcode.lc383_ransom_note;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/ransom-note/
 * #easy
 */
public final class LC383RansomNote {
    public final class Solution {
        public boolean canConstruct(final String ransomNote, final String magazine) {
            int[] counts = new int[256];
            for (char letter : magazine.toCharArray()) {
                counts[letter]++;
            }
            for (char letter : ransomNote.toCharArray()) {
                if (--counts[letter] < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test_example_a_b() throws Exception {
        assertFalse(new Solution().canConstruct("a", "b"));
    }

    @Test
    public void test_example_aa_ab() throws Exception {
        assertFalse(new Solution().canConstruct("aa", "ab"));
    }

    @Test
    public void test_example_aa_aab() throws Exception {
        assertTrue(new Solution().canConstruct("aa", "aab"));
    }
}
