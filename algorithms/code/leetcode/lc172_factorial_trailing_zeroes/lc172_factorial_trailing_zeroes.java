package leetcode.lc172_factorial_trailing_zeroes;

/**
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 * #medium
 */
public final class LC172FactorialTrailingZeroes {
    public int trailingZeroes(final int n) {
        long step = 5;
        long count = 0;
        while (step <= n) {
            count += n / step;
            step = step * 5;
        }
        return (int) count;
    }
}
package leetcode.lc172_factorial_trailing_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC172FactorialTrailingZeroesTests {
    @Test
    public void test5() throws Exception {
        assertEquals(1, new LC172FactorialTrailingZeroes().trailingZeroes(5));
    }

    @Test
    public void test1808548329() throws Exception {
        assertEquals(452137076, new LC172FactorialTrailingZeroes().trailingZeroes(1808548329));
    }

    @Test
    public void test2147483647() throws Exception {
        assertEquals(536870902, new LC172FactorialTrailingZeroes().trailingZeroes(2147483647));
    }
}
```rust
// This file contains the implementation of a function to calculate the trailing zeroes in the factorial of a number.

use std::convert::TryInto;

struct Lc172FactorialTrailingZeroes {
    fn trailing_zeroes(self, n: i64) -> usize {
        let mut step = 5;
        let mut count = 0;
        while step <= n {
            count += (n as i64 / step) as usize;
            step *= 5;
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_5() {
        assert_eq!(1, Lc172FactorialTrailingZeroes::trailing_zeroes(5));
    }

    #[test]
    fn test_1808548329() {
        assert_eq!(452137076, Lc172FactorialTrailingZeroes::trailing_zeroes(1808548329));
    }

    #[test]
    fn test_2147483647() {
        assert_eq!(536870902, Lc172FactorialTrailingZeroes::trailing_zeroes(2147483647));
    }
}
```