package leetcode.lc214_shortest_palindrome;

/**
 * https://leetcode.com/problems/shortest-palindrome/ #hard
 */
public final class LC214ShortestPalindrome {
    public String shortestPalindrome(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String a = s + new StringBuffer(s).reverse().toString();
        int[] cont = new int[a.length()];
        cont[0] = 0;
        for (int i = 1; i < a.length(); i++) {
            int index = cont[i - 1];
            while (index > 0 && a.charAt(index) != a.charAt(i)) {
                index = cont[index - 1];
            }
            cont[i] = index + (a.charAt(index) == a.charAt(i) ? 1 : 0);
        }
        return new StringBuilder(s.substring(cont[cont.length - 1], s.length())).reverse().toString() + s;
    }
}
package leetcode.lc214_shortest_palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC214ShortestPalindromeTests {
    @Test
    public void testAacecaaa() throws Exception {
        assertEquals("aaacecaaa", new LC214ShortestPalindrome().shortestPalindrome("aacecaaa"));
    }

    @Test
    public void testAbcd() throws Exception {
        assertEquals("dcbabcd", new LC214ShortestPalindrome().shortestPalindrome("abcd"));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC214ShortestPalindrome().shortestPalindrome(null));
        assertEquals("", new LC214ShortestPalindrome().shortestPalindrome(""));
    }
}
```rust
struct LC214ShortestPalindrome;

impl LC214ShortestPalindrome {
    fn shortest_palindrome(s: &str) -> String {
        if s.is_empty() || s.len() == 0 {
            return s.to_string();
        }

        let mut a = format!("{}{}", s, s.chars().rev().collect::<String>());
        let mut cont = vec![0; a.len()];

        cont[0] = 0;
        for i in 1..a.len() {
            let index = cont[i - 1];
            while index > 0 && a.as_bytes()[index] != a.as_bytes()[i as usize] {
                index = cont[index - 1];
            }
            cont[i] = index + if a.as_bytes()[index] == a.as_bytes()[i as usize] { 1 } else { 0 };
        }

        format!("{}{}", s.chars().rev().collect::<String>().as_str(), &s[..cont[cont.len() - 1]])
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_aacecaaa() {
        assert_eq!(format!("aaacecaaa"), LC214ShortestPalindrome::shortest_palindrome("aacecaaa"));
    }

    #[test]
    fn test_abcd() {
        assert_eq!(format!("dcbabcd"), LC214ShortestPalindrome::shortest_palindrome("abcd"));
    }

    #[test]
    fn test_nothing() {
        assert!(LC214ShortestPalindrome::shortest_palindrome(null).is_null());
        assert_eq!(format!(""), LC214ShortestPalindrome::shortest_palindrome(""));
    }
}
```