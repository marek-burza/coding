package leetcode.lc326_power_of_three;

/**
 * https://leetcode.com/problems/power-of-three/ #easy
 * <p/>
 * To do it without a loop resort to logarithms (but beware of accuracy)
 */
public final class LC326PowerOfThree {
    public boolean isPowerOfThree(final int nValue) {
        int n = nValue;
        if (n < 1) {
            return false;
        }
        while (1 < n) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
package leetcode.lc326_power_of_three;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC326PowerOfThreeTests {
    @Test
    public void test27() throws Exception {
        assertTrue(new LC326PowerOfThree().isPowerOfThree(27));
    }

    @Test
    public void test11() throws Exception {
        assertFalse(new LC326PowerOfThree().isPowerOfThree(11));
    }

    @Test
    public void test1() throws Exception {
        assertTrue(new LC326PowerOfThree().isPowerOfThree(1));
    }

    @Test
    public void test0() throws Exception {
        assertEquals(new LC326PowerOfThree().isPowerOfThree(0), false);
    }

    @Test
    public void testMinus3() throws Exception {
        assertEquals(new LC326PowerOfThree().isPowerOfThree(-3), false);
    }
}
```rust
use std::ops::{Div, Mul};

fn is_power_of_three(n_value: i32) -> bool {
    let n = n_value;
    if n < 1 {
        return false;
    }
    while n > 1 {
        if n % 3 != 0 {
            return false;
        }
        n /= 3;
    }
    true
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test27() {
        assert!(is_power_of_three(27));
    }

    #[test]
    fn test11() {
        assert!(!is_power_of_three(11));
    }

    #[test]
    fn test1() {
        assert!(is_power_of_three(1));
    }

    #[test]
    fn test0() {
        assert!(is_power_of_three(0) == false);
    }

    #[test]
    fn testMinus3() {
        assert!(!is_power_of_three(-3));
    }
}
```