package leetcode.lc290_word_pattern;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/word-pattern/ #easy
 */
public final class LC290WordPattern {
    private boolean check(final String first, final String second, final HashMap<String, String> mapping) {
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
