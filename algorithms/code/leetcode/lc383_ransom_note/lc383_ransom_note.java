package leetcode.lc383_ransom_note;

/**
 * https://leetcode.com/problems/ransom-note/ #easy
 */
public final class LC383RansomNote {
    public boolean canConstruct(final String ransomNote, final String magazine) {
        int[] counts = new int[256];
        for (char letter : magazine.toCharArray()) {
            counts[letter]++;
        }
        for (char letter : ransomNote.toCharArray()) {
            if (--counts[letter] < 0) {
                return false;
            }
        }
        return true;
    }
}
package leetcode.lc383_ransom_note;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC383RansomNoteTests {
    @Test
    public void testExampleAB() throws Exception {
        assertFalse(new LC383RansomNote().canConstruct("a", "b"));
    }

    @Test
    public void testExampleAaAb() throws Exception {
        assertFalse(new LC383RansomNote().canConstruct("aa", "ab"));
    }

    @Test
    public void testExampleAaAab() throws Exception {
        assertTrue(new LC383RansomNote().canConstruct("aa", "aab"));
    }
}
```rust
// https://leetcode.com/problems/ransom-note/ #easy
pub struct LC383RansomNote;

impl LC383RansomNote {
    /// Checks if it's possible to form the ransom note using the characters in the magazine.
    pub fn can_construct(&self, ransom_note: &str, magazine: &str) -> bool {
        let mut counts = [0; 256];

        // Count the occurrences of each character in the magazine
        for letter in magazine.chars() {
            counts[letter as usize] += 1;
        }

        // Check if the ransom note can be constructed from the magazine
        for letter in ransom_note.chars() {
            if counts[letter as usize] <= 0 {
                return false;
            }
            counts[letter as usize] -= 1;
        }

        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_ab() {
        assert!(!LC383RansomNote::can_construct("a", "b"));
    }

    #[test]
    fn test_example_aa_ab() {
        assert!(!LC383RansomNote::can_construct("aa", "ab"));
    }

    #[test]
    fn test_example_aa_aab() {
        assert!(LC383RansomNote::can_construct("aa", "aab"));
    }
}
```