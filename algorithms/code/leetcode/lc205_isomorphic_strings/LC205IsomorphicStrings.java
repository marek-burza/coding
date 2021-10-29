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
