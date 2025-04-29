package leetcode.lc058_length_of_last_word;

/**
 * https://leetcode.com/problems/length-of-last-word/ #easy
 */
public final class LC058LengthOfLastWord {
    public int lengthOfLastWord(final String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        while (n > 0 && s.charAt(n - 1) == ' ') {
            n--;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                return n - i - 1;
            }
        }
        return n;
    }
}
package leetcode.lc058_length_of_last_word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC058LengthOfLastWordTests {
    @Test
    public void testHelloWorld() throws Exception {
        assertEquals(5, new LC058LengthOfLastWord().lengthOfLastWord("Hello World"));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(null));
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(""));
    }

    @Test
    public void testAlmostNothing() throws Exception {
        assertEquals(0, new LC058LengthOfLastWord().lengthOfLastWord(" "));
    }

    @Test
    public void testTrailingSpace() throws Exception {
        assertEquals(5, new LC058LengthOfLastWord().lengthOfLastWord("Hello World  "));
    }

    @Test
    public void testSingleWord() throws Exception {
        assertEquals(10, new LC058LengthOfLastWord().lengthOfLastWord("HelloWorld"));
    }
}
```rust
fn length_of_last_word(s: &str) -> i32 {
    if s.is_empty() || s.trim().is_empty() {
        return 0;
    }

    let n = s.len();
    while n > 0 && s.chars().last().unwrap() == ' ' {
        n -= 1;
    }
    for (i, c) in s.chars().rev().enumerate() {
        if c == ' ' {
            return n - i as i32 - 1;
        }
    }
    n
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_hello_world() {
        assert_eq!(length_of_last_word("Hello World"), 5);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(length_of_last_word(""), 0);
        assert_eq!(length_of_last_word(" "), 0);
        assert_eq!(length_of_last_word("   "), 0);
    }

    #[test]
    fn test_trailing_space() {
        assert_eq!(length_of_last_word("Hello World  "), 5);
    }

    #[test]
    fn test_single_word() {
        assert_eq!(length_of_last_word("HelloWorld"), 10);
    }
}
```