package leetcode.lc049_group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/
 * #medium
 */
public final class LC049GroupAnagrams {
    public List<List<String>> groupAnagrams(final String[] strs) {
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
        ArrayList<List<String>> result = new ArrayList<>();
        for (List<String> group : seen.values()) {
            Collections.sort(group);
            result.add(group);
        }
        return result;
    }
}
