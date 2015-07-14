package leetcode;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 */
public class LC205IsomorphicStrings {
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

    public static void main(String[] arguments) {
        System.out.println(new LC205IsomorphicStrings().isIsomorphic("aa", "ab"));
    }
}