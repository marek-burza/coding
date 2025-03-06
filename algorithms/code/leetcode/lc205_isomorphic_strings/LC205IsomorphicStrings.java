package leetcode.lc205_isomorphic_strings;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/isomorphic-strings/ #easy
 */
public final class LC205IsomorphicStrings {
    public boolean isIsomorphic(final String s, final String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character source = s.charAt(i);
            Character target = t.charAt(i);
            if (map.containsKey(source)) {
                if (target != map.get(source).charValue()) {
                    return false;
                }
            } else {
                if (map.containsValue(target)) {
                    return false;
                }
                map.put(source, target);
            }
        }
        return true;
    }
}
package leetcode.lc205_isomorphic_strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public final class LC205IsomorphicStringsTests {
    @Test
    public void testAaAndAb() throws Exception {
        assertFalse(new LC205IsomorphicStrings().isIsomorphic("aa", "ab"));
    }

    @Test
    public void testEggAndAdd() throws Exception {
        assertTrue(new LC205IsomorphicStrings().isIsomorphic("egg", "add"));
    }

    @Test
    public void testAcAndBb() throws Exception {
        assertFalse(new LC205IsomorphicStrings().isIsomorphic("ac", "bb"));
    }
}
