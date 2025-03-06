package leetcode.lc242_valid_anagram;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/ #easy
 */
public final class LC242ValidAnagram {
    public boolean isAnagram(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.compare(sChars, tChars) == 0;
    }
}
package leetcode.lc242_valid_anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC242ValidAnagramTests {
    @Test
    public void testAaA() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("aa", "a"));
    }

    @Test
    public void testAB() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("a", "b"));
    }

    @Test
    public void testAnagramNagaram() throws Exception {
        assertTrue(new LC242ValidAnagram().isAnagram("anagram", "nagaram"));
    }

    @Test
    public void testRatCar() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("rat", "car"));
    }
}
