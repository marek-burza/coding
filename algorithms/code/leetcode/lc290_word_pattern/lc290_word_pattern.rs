package leetcode.lc290_word_pattern;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/word-pattern/ #easy
 */
public final class LC290WordPattern {
    private boolean check(final String first, final String second, final HashMap<String, String> mapping) {
        if (mapping.containsKey(first)) {
            if (!mapping.get(first).equals(second)) {
                return false;
            }
        } else {
            mapping.put(first, second);
        }
        return true;
    }

    public boolean wordPattern(final String pattern, final String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        HashMap<String, String> mappingPS = new HashMap<>();
        HashMap<String, String> mappingSP = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String key = pattern.substring(i, i + 1);
            if (!check(key, words[i], mappingPS) || !check(words[i], key, mappingSP)) {
                return false;
            }
        }
        return true;
    }
}
package leetcode.lc290_word_pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC290WordPatternTests {
    @Test
    public void testAbbaDogCatCatDog() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogCatCatFish() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog cat cat fish"));
    }

    @Test
    public void testAaaaDogCatCatDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("aaaa", "dog cat cat dog"));
    }

    @Test
    public void testAbbaDogDogDogDog() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("abba", "dog dog dog dog"));
    }

    @Test
    public void testAbBC() throws Exception {
        assertTrue(new LC290WordPattern().wordPattern("ab", "b c"));
    }

    @Test
    public void testMismatched() throws Exception {
        assertFalse(new LC290WordPattern().wordPattern("ab", "c"));
    }
}
```rust
use std::collections::HashMap;

pub struct LC290WordPattern {
    /// Checks if a pair of words and pattern mapping is valid.
    fn check(&self, first: &str, second: &str, mapping: &mut HashMap<String, String>) -> bool {
        // If the word is already in the mapping with a different value,
        // return false
        if mapping.contains_key(first) && mapping.get(first).unwrap() != second {
            return false;
        }
        // Add the new mapping entry
        else if !mapping.insert(first.to_string(), second.to_string()).is_none {
            panic!("Mapping cannot be modified");
        }
        true
    }

    /// Checks if a word pattern matches a given string.
    pub fn word_pattern(&self, pattern: &str, str: &str) -> bool {
        // Split the input string into words
        let mut words = str.split(" ");
        // Check if the lengths of the pattern and words match
        if pattern.len() != words.len() {
            return false;
        }
        // Initialize two hash maps for mapping pattern to word and word to pattern
        let mut mappingPS = HashMap::new();
        let mut mappingSP = HashMap::new();
        for i in 0..pattern.len() {
            // Extract the current character from the pattern
            let key = &pattern[i];
            // Check if the pattern character matches with a word and vice versa
            if !self.check(key, words[i], &mut mappingPS) || !self.check(words[i], key, &mut mappingSP) {
                return false;
            }
        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_abba_dog_cat_cat_dog() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(lc.word_pattern("abba", "dog cat cat dog"));
        Ok(())
    }

    #[test]
    fn test_abba_dog_cat_cat_fish() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(!lc.word_pattern("abba", "dog cat cat fish"));
        Ok(())
    }

    #[test]
    fn testaaaa_dog_cat_cat_dog() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(!lc.word_pattern("aaaa", "dog cat cat dog"));
        Ok(())
    }

    #[test]
    fn test_abba_dog_dog_dog_dog() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(!lc.word_pattern("abba", "dog dog dog dog"));
        Ok(())
    }

    #[test]
    fn test_ab_bc() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(lc.word_pattern("ab", "b c"));
        Ok(())
    }

    #[test]
    fn test_mismatched() -> Result<(), Box<dyn std::error::Error>> {
        let lc = LC290WordPattern {};
        assert!(!lc.word_pattern("ab", "c"));
        Ok(())
    }
}
```
