package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LC014LongestCommonPrefix {
    public class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int i = 0;
            boolean done = false;
            while (!done) {
                for (String str : strs) {
                    if (str == null) {
                        return "";
                    }
                    if (i >= str.length() || str.charAt(i) != strs[0].charAt(i)) {
                        done = true;
                        break;
                    }
                }
                i++;
            }
            return strs[0].substring(0, i - 1);
        }
    }

    @Test
    public void test_Ala_AlaMaKota() throws Exception {
        assertEquals("Ala", new Solution().longestCommonPrefix(new String[]{"Ala", "Ala Ma Kota"}));
    }

    @Test
    public void test_aa_a() throws Exception {
        assertEquals("a", new Solution().longestCommonPrefix(new String[]{"aa", "a"}));
    }

    @Test
    public void test_None() throws Exception {
        assertEquals("", new Solution().longestCommonPrefix(new String[]{}));
    }

    @Test
    public void test_Empty_b() throws Exception {
        assertEquals("", new Solution().longestCommonPrefix(new String[]{"", "b"}));
    }
}
