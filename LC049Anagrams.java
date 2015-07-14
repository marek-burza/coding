package leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/anagrams/
 */
public class LC049Anagrams {
    public List<String> anagrams(String[] strs) {
        Map<String, List<String>> seen = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            if (seen.containsKey(key)) {
                seen.get(key).add(str);
            } else {
                List<String> group = new ArrayList<>();
                group.add(str);
                seen.put(key, group);
            }
        }
        ArrayList<String> list = new ArrayList<>();
        for (List<String> group : seen.values()) {
            if (group.size() > 1) {
                for (String str : group) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    public static void main(String[] arguments) {
        LC049Anagrams solution = new LC049Anagrams();
        String[] strs = {"abc", "cab", "bad", "dab", "zzz", "dot"};
        System.out.println(solution.anagrams(strs));
    }
}
