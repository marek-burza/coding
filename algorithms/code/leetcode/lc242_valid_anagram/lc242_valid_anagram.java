package leetcode.lc242_valid_anagram;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/ #easy
 */
public final class LC242ValidAnagram {
    public boolean isAnagram(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.compare(sChars, tChars) == 0;
    }
}
package leetcode.lc242_valid_anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC242ValidAnagramTests {
    @Test
    public void testAaA() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("aa", "a"));
    }

    @Test
    public void testAB() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("a", "b"));
    }

    @Test
    public void testAnagramNagaram() throws Exception {
        assertTrue(new LC242ValidAnagram().isAnagram("anagram", "nagaram"));
    }

    @Test
    public void testRatCar() throws Exception {
        assertFalse(new LC242ValidAnagram().isAnagram("rat", "car"));
    }
}
```rust
fn is_anagram(s: String, t: String) -> bool {
    if s.len() != t.len() {
        return false;
    }
    let mut s_chars = s.chars().collect::<Vec<char>>();
    let mut t_chars = t.chars().collect::<Vec<char>>();
    s_chars.sort();
    t_chars.sort();
    s_chars == t_chars
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aaA() {
        assert!(!is_anagram("aa".to_string(), "a"));
    }

    #[test]
    fn test_AB() {
        assert!(!is_anagram("a", "b"));
    }

    #[test]
    fn test_anagram_nagaram() {
        assert!(is_anagram("anagram".to_string(), "nagaram"));
    }

    #[test]
    fn test_rat_car() {
        assert!(!is_anagram("rat".to_string(), "car"));
    }
}
```