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
```rust
use std::collections::HashMap;

fn is_isomorphic(s: String, t: String) -> bool {
    let mut map = HashMap::new();
    for (source, target) in s.chars().zip(t.chars()) {
        if let Some(existing_target) = map.get(&source) {
            if *existing_target != target {
                return false;
            }
        } else if map.contains_key(&target) {
            return false;
        }
        map.insert(source, target);
    }
    true
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aa_and_ab() {
        assert!(!is_isomorphic("aa".to_string(), "ab".to_string()));
    }

    #[test]
    fn test_egg_and_add() {
        assert!(is_isomorphic("egg".to_string(), "add".to_string()));
    }

    #[test]
    fn test_ac_and_bb() {
        assert!(!is_isomorphic("ac".to_string(), "bb".to_string()));
    }
}
```