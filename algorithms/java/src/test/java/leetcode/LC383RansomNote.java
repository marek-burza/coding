package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/ransom-note/
 * #easy
 */
public class LC383RansomNote {
    public class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
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
        assertEquals(false, new Solution().canConstruct("a", "b"));
    }

    @Test
    public void test_example_aa_ab() throws Exception {
        assertEquals(false, new Solution().canConstruct("aa", "ab"));
    }

    @Test
    public void test_example_aa_aab() throws Exception {
        assertEquals(true, new Solution().canConstruct("aa", "aab"));
    }
}
