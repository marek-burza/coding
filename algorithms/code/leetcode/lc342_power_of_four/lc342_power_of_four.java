package leetcode.lc342_power_of_four;

/**
 * https://leetcode.com/problems/power-of-four/ #easy
 */
public final class LC342PowerOfFour {
    public boolean isPowerOfFour(final int num) {
        if (num <= 0) {
            return false;
        }
        double value = Math.log(num) / Math.log(4);
        return value == Math.floor(value);
    }
}
package leetcode.lc342_power_of_four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC342PowerOfFourTests {
    @Test
    public void test16() throws Exception {
        assertTrue(new LC342PowerOfFour().isPowerOfFour(16));
    }

    @Test
    public void test5() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(5));
    }

    @Test
    public void testNonPositive() throws Exception {
        assertFalse(new LC342PowerOfFour().isPowerOfFour(0));
        assertFalse(new LC342PowerOfFour().isPowerOfFour(-1));
    }
}
```rust
// leetcode/lc342_power_of_four.rs

/// Checks if a number is a power of four.
///
/// # Examples
///
/// ```
/// let mut solution = Solution;
/// assert!(solution.is_power_of_four(16)); // true
/// assert!(!solution.is_power_of_four(5)); // false
/// assert!(!solution.is_power_of_four(0)); // false
/// ```rust
pub struct Solution;

impl Solution {
    /// Checks if a number is a power of four.
    pub fn is_power_of_four(n: i32) -> bool {
        if n <= 0 {
            return false;
        }
        let mut value = (n as f64).log2() / (4.0 as f64).log2();
        value == value.floor() as f64
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test16() {
        assert!(Solution::is_power_of_four(16));
    }

    #[test]
    fn test5() {
        assert!(!Solution::is_power_of_four(5));
    }

    #[test]
    fn testNonPositive() {
        assert!(!Solution::is_power_of_four(0));
        assert!(!Solution::is_power_of_four(-1));
    }
}
```