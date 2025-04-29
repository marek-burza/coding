package leetcode.lc279_perfect_squares;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/perfect-squares/
 * #medium
 */
public final class LC279PerfectSquares {
    public int numSquares(final int n) {
        int[] lut = new int[n + 1];
        Arrays.fill(lut, 1, n + 1, Integer.MAX_VALUE);
        for (int i = 1, ii = 1; ii <= n; ii = ++i * i) {
            for (int j = ii; j < lut.length; j++) {
                lut[j] = Math.min(lut[j], lut[j - ii] + 1);
            }
        }
        return lut[n];
    }
}
package leetcode.lc279_perfect_squares;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC279PerfectSquaresTests {
    @Test
    public void test12() throws Exception {
        assertEquals(3, new LC279PerfectSquares().numSquares(12));
    }

    @Test
    public void test13() throws Exception {
        assertEquals(2, new LC279PerfectSquares().numSquares(13));
    }

    @Test
    public void test9975() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(9975));
    }

    @Test
    public void test9732() throws Exception {
        assertEquals(3, new LC279PerfectSquares().numSquares(9732));
    }

    @Test
    public void test5756() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(5756));
    }

    @Test
    public void test6255() throws Exception {
        assertEquals(4, new LC279PerfectSquares().numSquares(6255));
    }
}
```rust
fn num_squares(n: i32) -> i32 {
    let mut lut = vec![i32::MAX; n as usize + 1];
    lut[0] = 0;
    for ii in (1..).map(|i| i * i).take_while(|&ii| ii <= n) {
        for j in (ii..lut.len()).step_by(ii) {
            let mut val = lut[j / ii];
            for k in ((j - ii)..=j).step_by(ii) {
                val = val.min(lut[k] + 1);
            }
            if val != i32::MAX { lut[j] = val; }
        }
    }
    *lut.last().unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test12() {
        assert_eq!(num_squares(12), 3);
    }

    #[test]
    fn test13() {
        assert_eq!(num_squares(13), 2);
    }

    #[test]
    fn test9975() {
        assert_eq!(num_squares(9975), 4);
    }

    #[test]
    fn test9732() {
        assert_eq!(num_squares(9732), 3);
    }

    #[test]
    fn test5756() {
        assert_eq!(num_squares(5756), 4);
    }

    #[test]
    fn test6255() {
        assert_eq!(num_squares(6255), 4);
    }
}
```