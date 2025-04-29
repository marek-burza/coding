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
package leetcode.lc049_group_anagrams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class LC049GroupAnagramsTests {
    private static class OrderlyComparator implements Comparator<List<String>>, Serializable {
        @Override
        public int compare(final List<String> l1, final List<String> l2) {
            int difference = l1.size() - l2.size();
            if (0 != difference) {
                return difference;
            } else {
                for (int i = 0; i < l1.size(); i++) {
                    difference = l1.get(i).compareTo(l2.get(i));
                    if (0 != difference) {
                        return difference;
                    }
                }
                return 0;
            }
        }
    }

    public void test(final String[][] expected, final List<List<String>> result) throws Exception {
        Collections.sort(result, new OrderlyComparator());
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j));
            }
        }
    }

    @Test
    public void testAbcCabBadDabZzzDot() throws Exception {
        String[] strs = { "abc", "cab", "bad", "dab", "zzz", "dot" };
        String[][] expected = { { "dot" }, { "zzz" }, { "abc", "cab" }, { "bad", "dab" } };
        List<List<String>> result = new LC049GroupAnagrams().groupAnagrams(strs);
        test(expected, result);
    }

    @Test
    public void testTeaAndAteEatDen() throws Exception {
        String[] strs = { "tea", "and", "ate", "eat", "den" };
        String[][] expected = { { "and" }, { "den" }, { "ate", "eat", "tea" } };
        List<List<String>> result = new LC049GroupAnagrams().groupAnagrams(strs);
        test(expected, result);
    }
}
```rust
use std::collections::{HashMap, HashSet};

pub struct LC049GroupAnagrams {
    /// Sort the characters in each string to create a unique key for grouping anagrams.
    fn group_anagrams(&self, strs: Vec<String>) -> Vec<Vec<String>> {
        let mut seen = HashMap::<String, Vec<String>>::new();
        for s in strs {
            let mut sorted_s = String::new();
            for c in s.chars() {
                sorted_s.push(c);
            }
            sorted_s.sort();

            if let Some(mut group) = seen.get_mut(&sorted_s) {
                *group.push(s);
            } else {
                let mut new_group = Vec::new();
                new_group.push(s);
                *seen.entry(sorted_s.clone()).or_insert_with(|| vec![]).push(s);
            }
        }

        // Sort each group of anagrams.
        seen.into_values().map(|g| g.sort()).collect()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_group_anagrams() {
        let mut group_anagrams = LC049GroupAnagrams {};
        let input = vec!["abc".to_string(), "cab".to_string(), "bad".to_string(), "dab".to_string(), "zzz".to_string(), "dot".to_string()];
        let expected = vec![vec!["dot".to_string()], vec!["zzz".to_string()], vec!["abc".to_string(), "cab".to_string()], vec!["bad".to_string(), "dab".to_string()]];
        let result = group_anagrams.group_anagrams(input);
        assert_eq!(result, expected);
    }

    #[test]
    fn test_group_anagrams_2() {
        let mut group_anagrams = LC049GroupAnagrams {};
        let input = vec!["tea".to_string(), "and".to_string(), "ate".to_string(), "eat".to_string(), "den".to_string()];
        let expected = vec![vec!["and".to_string()], vec!["den".to_string()], vec!["ate".to_string(), "eat".to_string(), "tea".to_string()]];
        let result = group_anagrams.group_anagrams(input);
        assert_eq!(result, expected);
    }
}
```