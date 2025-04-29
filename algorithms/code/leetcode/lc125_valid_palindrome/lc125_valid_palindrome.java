package leetcode.lc125_valid_palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome/ #easy
 */
public final class LC125ValidPalindrome {
    public boolean isPalindrome(final String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            if (!Character.isLetterOrDigit(a)) {
                i++;
                continue;
            }
            char b = s.charAt(j);
            if (!Character.isLetterOrDigit(b)) {
                j--;
                continue;
            }
            if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}
package leetcode.lc125_valid_palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC125ValidPalindromeTests {
    @Test
    public void testAManAPlanACanalPanama() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testRaceACar() throws Exception {
        assertFalse(new LC125ValidPalindrome().isPalindrome("race a car"));
    }

    @Test
    public void testAva() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome("Ava"));
    }

    @Test
    public void testBurger() throws Exception {
        assertFalse(new LC125ValidPalindrome().isPalindrome("burger"));
    }

    @Test
    public void testNothing() throws Exception {
        assertTrue(new LC125ValidPalindrome().isPalindrome(null));
        assertTrue(new LC125ValidPalindrome().isPalindrome(""));
    }
}
```rust
fn is_palindrome(s: &str) -> bool {
    if s.is_empty() {
        return true;
    }

    let mut i = 0;
    let mut j = s.len() - 1;

    while i <= j {
        if !s.chars().nth(i).unwrap().is_ascii_graphic() {
            i += 1;
            continue;
        }
        if !s.chars().nth(j).unwrap().is_ascii_graphic() {
            j -= 1;
            continue;
        }
        if s.chars().nth(i).unwrap().to_ascii_uppercase() != s.chars().nth(j).unwrap().to_ascii_uppercase() {
            return false;
        } else {
            i += 1;
            j -= 1;
        }
    }

    true
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_a_man_a_plan_a_canal_panama() {
        assert!(is_palindrome("A man, a plan, a canal: Panama"));
    }

    #[test]
    fn test_race_a_car() {
        assert!(!is_palindrome("race a car"));
    }

    #[test]
    fn test_ava() {
        assert!(is_palindrome("Ava"));
    }

    #[test]
    fn test_burger() {
        assert!(!is_palindrome("burger"));
    }

    #[test]
    fn test_nothing() {
        assert!(is_palindrome(""));
        assert!(is_palindrome(null()));
    }
}
```