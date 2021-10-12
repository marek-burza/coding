package leetcode;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 * #easy
 */
public final class LC205IsomorphicStrings {
    public final class Solution {
        public boolean isIsomorphic(final String s, final String t) {
            HashMap<Character, Character> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Character source = s.charAt(i);
                Character target = t.charAt(i);
                if (map.containsKey(source)) {
                    if (target != map.get(source)) {
                        return false;
                    }
                } else {
                    if (map.containsValue(target)) {
                        return false;
                    } else {
                        map.put(source, target);
                    }
                }
            }
            return true;
        }
    }

    @Test
    public void test_aa__ab() throws Exception {
        assertEquals(false, new Solution().isIsomorphic("aa", "ab"));
    }
}