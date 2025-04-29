package leetcode.lc202_happy_number;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/ #easy
 */
public final class LC202HappyNumber {
    private int re(final int nValue) {
        int n = nValue;
        int result = 0;
        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            result += digit * digit;
        }
        return result;
    }

    public boolean isHappy(final int nValue) {
        int n = nValue;
        Set<Integer> seen = new HashSet<>();
        seen.add(n);
        while (n != 1) {
            n = re(n);
            if (seen.contains(n)) {
                return false;
            }
            seen.add(n);

        }
        return true;
    }
}
package leetcode.lc202_happy_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC202HappyNumberTests {
    @Test
    public void test19() throws Exception {
        assertTrue(new LC202HappyNumber().isHappy(19));
    }

    @Test
    public void test2() throws Exception {
        assertFalse(new LC202HappyNumber().isHappy(2));
    }
}
```rust
pub struct HappyNumber {
    /// Recursively calculate the sum of squares of digits for a given number.
    fn rec(&self, n: i32) -> i32 {
        let mut result = 0;
        while n != 0 {
            let digit = (n % 10) as i32; // cast to i32
            n /= 10;
            result += digit * digit;
        }
        return result;
    }

    /// Check if a number is happy.
    pub fn is_happy(&self, n: i32) -> bool {
        let mut seen = std::collections::HashSet::new();
        seen.insert(n);
        while n != 1 {
            n = self.rec(n);
            if seen.contains(&n) {
                return false;
            }
            seen.insert(n);

        }
        true
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test19() {
        assert!(HappyNumber::is_happy(19));
    }

    #[test]
    fn test2() {
        assert!(!HappyNumber::is_happy(2));
    }
}
```