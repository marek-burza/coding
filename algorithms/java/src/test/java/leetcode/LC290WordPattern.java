package leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/word-pattern/
 * #easy
 */
public class LC290WordPattern {
    public class Solution {
        public boolean check(String first, String second, HashMap<String, String> mapping) {
            if (mapping.containsKey(first)) {
                if (!mapping.get(first).equals(second)) {
                    return false;
                }
            } else {
                mapping.put(first, second);
            }
            return true;
        }

        public boolean wordPattern(String pattern, String str) {
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
        assertEquals(true, new Solution().wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void test_abba__dog_cat_cat_fish() throws Exception {
        assertEquals(false, new Solution().wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void test_aaaa__dog_cat_cat_dog() throws Exception {
        assertEquals(false, new Solution().wordPattern("aaaa", "dog cat cat dog"));
    }

    @Test
    public void test_abba__dog_dog_dog_dog() throws Exception {
        assertEquals(false, new Solution().wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void test_ab_b_c() throws Exception {
        assertEquals(true, new Solution().wordPattern("ab", "b c"));
    }
}
