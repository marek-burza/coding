package leetcode.lc070_climbing_stairs;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/climbing-stairs/ #easy
 */
public final class LC070ClimbingStairs {
    public int climbStairs(final int n, final int at, final int[] lut) {
        if (at + 2 == n) {
            return 2;
        }
        if (at + 1 == n) {
            return 1;
        }
        if (lut[at] == 0) {
            lut[at] = climbStairs(n, at + 1, lut) + climbStairs(n, at + 2, lut);
        }
        return lut[at];
    }

    public int climbStairs(final int n) {
        int[] lut = new int[n];
        Arrays.fill(lut, 0);
        return climbStairs(n, 0, lut);
    }
}
package leetcode.lc070_climbing_stairs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC070ClimbingStairsTests {
    @Test
    public void test20() throws Exception {
        assertEquals(10946, new LC070ClimbingStairs().climbStairs(20));
    }
}
```rust
pub fn climb_stairs(n: i32, at: i32, mut lut: Vec<i32>) -> i32 {
    if at + 2 == n {
        return 2;
    }
    if at + 1 == n {
        return 1;
    }
    if lut[at] == 0 {
        lut[at] = climb_stairs(n, at + 1, lut) + climb_stairs(n, at + 2, lut);
    }
    lut[at]
}

pub fn climb_stairs(n: i32) -> i32 {
    let mut lut = vec![0; n as usize];
    for _ in 0..n {
        lut[climb_stairs(n, 0, lut).abs() as usize] += 1;
    }
    lut[0]
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test20() {
        assert_eq!(climb_stairs(20), 10946);
    }
}
```