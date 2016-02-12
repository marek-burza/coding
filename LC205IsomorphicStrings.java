package leetcode;

import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 */
public class LC205IsomorphicStrings {
    public class Solution {
        public boolean isIsomorphic(String s, String t) {
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