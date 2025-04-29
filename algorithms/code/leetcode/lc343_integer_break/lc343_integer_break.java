package leetcode.lc343_integer_break;

/**
 * https://leetcode.com/problems/integer-break/
 * #medium
 */
public final class LC343IntegerBreak {
    public int integerBreak(final int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 5) {
            return 6;
        }
        int threes = n / 3;
        int rest = (n - 3 * (threes - 1));
        rest = rest == 5 ? 6 : rest;
        return (int) Math.pow(3, threes - 1) * rest;
        // int product = 1;
        // while(n > 4){
        // product *= 3;
        // n -= 3;
        // }
        // return product * n;
    }
}
package leetcode.lc343_integer_break;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC343IntegerBreakTests {
    @Test
    public void test2() throws Exception {
        assertEquals(1, new LC343IntegerBreak().integerBreak(2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(2, new LC343IntegerBreak().integerBreak(3));
    }

    @Test
    public void test4() throws Exception {
        assertEquals(4, new LC343IntegerBreak().integerBreak(4));
    }

    @Test
    public void test5() throws Exception {
        assertEquals(6, new LC343IntegerBreak().integerBreak(5));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(9, new LC343IntegerBreak().integerBreak(6));
    }

    @Test
    public void test7() throws Exception {
        assertEquals(12, new LC343IntegerBreak().integerBreak(7));
    }

    @Test
    public void test8() throws Exception {
        assertEquals(18, new LC343IntegerBreak().integerBreak(8));
    }

    @Test
    public void test9() throws Exception {
        assertEquals(27, new LC343IntegerBreak().integerBreak(9));
    }

    @Test
    public void test10() throws Exception {
        assertEquals(36, new LC343IntegerBreak().integerBreak(10));
    }
}
```rust
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn integer_break_2() {
        assert_eq!(1, integer_break(2));
    }

    #[test]
    fn integer_break_3() {
        assert_eq!(2, integer_break(3));
    }

    #[test]
    fn integer_break_4() {
        assert_eq!(4, integer_break(4));
    }

    #[test]
    fn integer_break_5() {
        assert_eq!(6, integer_break(5));
    }

    #[test]
    fn integer_break_6() {
        assert_eq!(9, integer_break(6));
    }

    #[test]
    fn integer_break_7() {
        assert_eq!(12, integer_break(7));
    }

    #[test]
    fn integer_break_8() {
        assert_eq!(18, integer_break(8));
    }

    #[test]
    fn integer_break_9() {
        assert_eq!(27, integer_break(9));
    }

    #[test]
    fn integer_break_10() {
        assert_eq!(36, integer_break(10));
    }
}

pub fn integer_break(n: i32) -> u64 {
    if n == 2 {
        return 1;
    }
    if n == 3 {
        return 2;
    }
    if n == 5 {
        return 6;
    }

    let threes = n / 3;
    let rest = (n - 3 * (threes - 1)).max(0);
    let mut result = 1u64.pow(threes as u32 - 1) * rest as u64;

    for i in (4..=n).step_by(3) {
        result *= 3;
    }

    result
}
```