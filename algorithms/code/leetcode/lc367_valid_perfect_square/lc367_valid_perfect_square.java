package leetcode.lc367_valid_perfect_square;

/**
 * https://leetcode.com/problems/valid-perfect-square/ #easy
 */
public final class LC367ValidPerfectSquare {
    public boolean isPerfectSquare(final int num) {
        long a = 0;
        long z = 1 + num / 2;
        while (a <= z) {
            long m = (a + z) >>> 1;
            long mm = m * m;
            if (mm == num) {
                return true;
            }
            if (mm > num) {
                z = m - 1;
            } else {
                a = m + 1;
            }
        }
        return false;
    }
}
package leetcode.lc367_valid_perfect_square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC367ValidPerfectSquareTests {
    @Test
    public void test1() throws Exception {
        assertTrue(new LC367ValidPerfectSquare().isPerfectSquare(1));
    }

    @Test
    public void test14() throws Exception {
        assertFalse(new LC367ValidPerfectSquare().isPerfectSquare(14));
    }

    @Test
    public void test16() throws Exception {
        assertTrue(new LC367ValidPerfectSquare().isPerfectSquare(16));
    }

    @Test
    public void testMaximum() throws Exception {
        assertFalse(new LC367ValidPerfectSquare().isPerfectSquare(Integer.MAX_VALUE));
    }
}
```rust
// leetcode/lc367_valid_perfect_square.rs

pub struct LC367ValidPerfectSquare {}

impl LC367ValidPerfectSquare {
    pub fn is_perfect_square(&self, num: i32) -> bool {
        let mut a = 0;
        let mut z = 1 + (num as f64 / 2.0).sqrt() as i32;
        while a <= z {
            let m = ((a - z) / 2 + z) as i32;
            let mm = m * m;
            if mm == num {
                return true;
            }
            if mm > num {
                z = m - 1;
            } else {
                a = m + 1;
            }
        }
        false
    }

    // TODO: Implement tests for LC367ValidPerfectSquare
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1() {
        assert!(LC367ValidPerfectSquare().is_perfect_square(1));
    }

    #[test]
    fn test14() {
        assert!(!LC367ValidPerfectSquare().is_perfect_square(14));
    }

    #[test]
    fn test16() {
        assert!(LC367ValidPerfectSquare().is_perfect_square(16));
    }

    // TODO: Implement more tests
}
```