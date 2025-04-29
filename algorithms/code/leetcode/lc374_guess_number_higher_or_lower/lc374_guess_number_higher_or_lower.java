package leetcode.lc374_guess_number_higher_or_lower;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/ #easy
 */
public final class LC374GuessNumberHigherOrLower {
    public int x;

    protected int guess(final int n) {
        if (x < n) {
            return -1;
        }
        if (x > n) {
            return 1;
        }
        return 0;
    }

    public int guessNumber(final int n) {
        int a = 1;
        int z = n;
        while (a != z) {
            int checking = (int) (((long) a + (long) z) / 2);
            switch (guess(checking)) {
            case 1:
                a = checking + 1;
                break;
            case -1:
                z = checking - 1;
                break;
            default:
                return checking;
            }
        }
        return a;
    }
}
package leetcode.lc374_guess_number_higher_or_lower;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC374GuessNumberHigherOrLowerTests {
    @Test
    public void test2In10() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 2;
        assertEquals(2, solution.guessNumber(10));
    }

    @Test
    public void test8In10() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 8;
        assertEquals(8, solution.guessNumber(10));
    }

    @Test
    public void test65789() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 65789;
        assertEquals(solution.x, solution.guessNumber(Integer.MAX_VALUE));
    }

    @Test
    public void test1() throws Exception {
        LC374GuessNumberHigherOrLower solution = new LC374GuessNumberHigherOrLower();
        solution.x = 1;
        assertEquals(solution.x, solution.guessNumber(1));
    }
}
```rust
// Guess Number Higher Or Lower
// https://leetcode.com/problems/guess-number-higher-or-lower/#easy

pub struct LC374GuessNumberHigherOrLower {
    x: i32,
}

impl LC374GuessNumberHigherOrLower {
    pub fn new() -> Self {
        LC374GuessNumberHigherOrLower { x: 0 }
    }

    // guessing function
    fn guess(&self, n: i32) -> i32 {
        if self.x < n {
            return -1;
        }
        if self.x > n {
            return 1;
        }
        return 0;
    }

    // binary search solution
    pub fn guess_number(&mut self, n: i32) -> i32 {
        let mut a = 1;
        let mut z = n;
        while a != z {
            let checking = (a + z) / 2;
            match self.guess(checking) {
                1 => {
                    a = checking + 1;
                }
                -1 => {
                    z = checking - 1;
                }
                _ => break,
            }
        }
        a
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test2_in_10() {
        let mut solution = LC374GuessNumberHigherOrLower::new();
        solution.x = 2;
        assert_eq!(solution.guess_number(10), 2);
    }

    #[test]
    fn test8_in_10() {
        let mut solution = LC374GuessNumberHigherOrLower::new();
        solution.x = 8;
        assert_eq!(solution.guess_number(10), 8);
    }

    #[test]
    fn test65789() {
        let mut solution = LC374GuessNumberHigherOrLower::new();
        solution.x = 65789;
        assert_eq!(solution.guess_number(i32::MAX), 65789);
    }

    #[test]
    fn test1() {
        let mut solution = LC374GuessNumberHigherOrLower::new();
        solution.x = 1;
        assert_eq!(solution.guess_number(1), 1);
    }
}
```