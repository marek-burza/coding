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
package leetcode.lc290_word_pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC290WordPatternTests {
    @Test
    public void testAbbaDogCatCatDog() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogCatCatFish() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void testAaaaDogCatCatDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("aaaa", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogDogDogDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void testAbBC() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("ab", "b c"));
    }

    @Test
    public void testMismatched() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("ab", "c"));
    }
}
