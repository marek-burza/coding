package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/implement-strstr/
 */
public class LC028ImplementStrStr {
    public class Solution {
        public int strStr(String haystack, String needle) {
            if (haystack.length() < needle.length()) return -1;
            for (int i = 0; i <= haystack.length() - needle.length(); i++){
                if(haystack.substring(i, i + needle.length()).equals(needle)){
                    return i;
                }
            }
            return -1;
        }
    }

    @Test
    public void test_empty() throws Exception {
        assertEquals(0, new Solution().strStr("", ""));
    }

    @Test
    public void test_mississippi_a() throws Exception {
        assertEquals(-1, new Solution().strStr("mississippi", "a"));
    }

    @Test
    public void test_mississippi_si() throws Exception {
        assertEquals(3, new Solution().strStr("mississippi", "si"));
    }
}
