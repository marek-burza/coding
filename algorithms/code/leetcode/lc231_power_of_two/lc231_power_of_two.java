package leetcode.lc231_power_of_two;

/**
 * https://leetcode.com/problems/power-of-two/ #easy
 */
public final class LC231PowerOfTwo {
    public boolean isPowerOfTwo(final int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            count += ((n & mask) == 0) ? 0 : 1;
        }
        return count == 1;
    }
}
package leetcode.lc231_power_of_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC231PowerOfTwoTests {
    @Test
    public void testMinus10() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(-10));
    }

    @Test
    public void test0() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(0));
    }

    @Test
    public void test1() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(1));
    }

    @Test
    public void test2() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(2));
    }

    @Test
    public void test5() throws Exception {
        assertFalse(new LC231PowerOfTwo().isPowerOfTwo(5));
    }

    @Test
    public void test1024() throws Exception {
        assertTrue(new LC231PowerOfTwo().isPowerOfTwo(1024));
    }
}
```rust
#[derive(Debug)]
pub struct LC231PowerOfTwo;

impl LC231PowerOfTwo {
    /// Checks if the number is a power of two.
    pub fn is_power_of_two(&self, n: i32) -> bool {
        if n <= 0 {
            return false;
        }
        let mut count = 0;
        let mut mask = 1;
        while mask != 0 {
            if ((n & mask) == 0) {
                count += 1;
            }
            mask <<= 1;
        }
        count == 1
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_minus10() {
        assert!(!LC231PowerOfTwo().is_power_of_two(-10));
    }

    #[test]
    fn test_0() {
        assert!(!LC231PowerOfTwo().is_power_of_two(0));
    }

    #[test]
    fn test_1() {
        assert!(LC231PowerOfTwo().is_power_of_two(1));
    }

    #[test]
    fn test_2() {
        assert!(LC231PowerOfTwo().is_power_of_two(2));
    }

    #[test]
    fn test_5() {
        assert!(!LC231PowerOfTwo().is_power_of_two(5));
    }

    #[test]
    fn test_1024() {
        assert!(LC231PowerOfTwo().is_power_of_two(1024));
    }
}
```