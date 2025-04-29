package leetcode.lc387_first_unique_character_in_a_string;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/ #easy
 */
public final class LC387FirstUniqueCharacterInAString {
    public int firstUniqChar(final String s) {
        int size = 'z' - 'a' + 1;
        int[] count = new int[size];
        int[] index = new int[size];
        int length = s.length();
        for (int i = length - 1; 0 <= i; i--) {
            int key = s.charAt(i) - 'a';
            index[key] = i;
            count[key]++;
        }
        int min = -1;
        for (int i = 0; i < size; i++) {
            if (count[i] == 1) {
                if (min == -1 || index[i] < min) {
                    min = index[i];
                }
            }
        }
        return min;
    }
}
package leetcode.lc387_first_unique_character_in_a_string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC387FirstUniqueCharacterInAStringTests {
    @Test
    public void testLeetcode() throws Exception {
        assertEquals(0, new LC387FirstUniqueCharacterInAString().firstUniqChar("leetcode"));
    }

    @Test
    public void testLoveleetcode() throws Exception {
        assertEquals(2, new LC387FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
    }

    @Test
    public void testEmpty() throws Exception {
        assertEquals(-1, new LC387FirstUniqueCharacterInAString().firstUniqChar(""));
    }
}
```rust
use std::collections::HashMap;

pub struct Solution {}

impl Solution {
    pub fn first_uniq_char(s: String) -> i32 {
        let size = b'z' - b'a' + 1;
        let mut count: HashMap<i32, i32> = HashMap::new();
        let length = s.len() as i32;

        for (i, c) in s.chars().rev().enumerate() {
            if !count.contains_key(&(c as i32 - 'a' as i32)) {
                *count.entry(c as i32 - 'a' as i32).or_insert(0) += 1;
            }
            count.insert((c as i32 - 'a' as i32), i);
        }

        let mut min = None;

        for (k, v) in &count {
            if *v == 1 && min.is_none() || min == Some(*v) {
                min = Some(*v + 'a' as i32);
            }
        }

        match min {
            Some(min_value) => min_value as i32,
            None => -1,
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_leetcode() {
        assert_eq!(Solution::first_uniq_char("leetcode".to_string()), 0);
    }

    #[test]
    fn test_loveleetcode() {
        assert_eq!(Solution::first_uniq_char("loveleetcode".to_string()), 2);
    }

    #[test]
    fn test_empty() {
        assert_eq!(Solution::first_uniq_char(String::new()), -1);
    }
}
```