package leetcode.lc290_word_pattern;

import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/word-pattern/
 * #easy
 */
public final class LC290WordPattern {
    public final class Solution {
        public boolean check(final String first, final String second, final HashMap<String, String> mapping) {
            if (mapping.containsKey(first)) {
                if (!mapping.get(first).equals(second)) {
                    return false;
                }
            } else {
                mapping.put(first, second);
            }
            return true;
        }

        public boolean wordPattern(final String pattern, final String str) {
            String[] words = str.split(" ");
            if (pattern.length() != words.length) {
                return false;
            }
            HashMap<String, String> mappingPS = new HashMap<>();
            HashMap<String, String> mappingSP = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String key = pattern.substring(i, i + 1);
                if (!check(key, words[i], mappingPS) || !check(words[i], key, mappingSP)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test_abba__dog_cat_cat_dog() throws Exception {
        assertTrue(new Solution().wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void test_abba__dog_cat_cat_fish() throws Exception {
        assertFalse(new Solution().wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void test_aaaa__dog_cat_cat_dog() throws Exception {
        assertFalse(new Solution().wordPattern("aaaa", "dog cat cat dog"));
    }

    @Test
    public void test_abba__dog_dog_dog_dog() throws Exception {
        assertFalse(new Solution().wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void test_ab_b_c() throws Exception {
        assertTrue(new Solution().wordPattern("ab", "b c"));
    }
}
