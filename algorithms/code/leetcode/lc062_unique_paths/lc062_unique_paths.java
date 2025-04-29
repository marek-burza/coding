package leetcode.lc062_unique_paths;

/**
 * https://leetcode.com/problems/unique-paths/
 * #medium
 */
public final class LC062UniquePaths {
    private long nck(final long n, final long kValue) {
        long k = kValue;
        if (k > n) {
            return 0;
        }
        if (k * 2 > n) {
            k = n - k;
        }
        if (k == 0) {
            return 1;
        }
        long r = n;
        for (long i = 2; i <= k; ++i) {
            r *= n - i + 1;
            r /= i;
        }
        return r;
    }

    public int uniquePaths(final int mValue, final int n) {
        int m = mValue;
        m--;
        return (int) nck(m + n - 1, m);
    }
}
package leetcode.lc062_unique_paths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC062UniquePathsTests {
    @Test
    public void test37() throws Exception {
        assertEquals(28, new LC062UniquePaths().uniquePaths(3, 7));
    }

    @Test
    public void test595() throws Exception {
        assertEquals(557845, new LC062UniquePaths().uniquePaths(59, 5));
    }

    @Test
    public void test110() throws Exception {
        assertEquals(1, new LC062UniquePaths().uniquePaths(1, 10));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC062UniquePaths().uniquePaths(1, 0));
    }
}
```rust
// Unique Paths
// https://leetcode.com/problems/unique-paths/
// #medium

pub struct UniquePaths {}

impl UniquePaths {
    /// Calculate the number of unique paths using Catalan numbers.
    ///
    /// # Parameters
    /// * `n`: The total steps.
    /// * `kValue`: The number of vertical jumps.
    pub fn nck(&self, n: i64, k_value: i64) -> i64 {
        let mut k = k_value;
        if k > n {
            return 0;
        }
        if k * 2 > n {
            k = n - k;
        }
        if k == 0 {
            return 1;
        }
        let mut r = n;
        for i in 2..=k {
            r *= (n + 1 - i) as i64;
            r /= i as i64;
        }
        r
    }

    /// Calculate the number of unique paths.
    ///
    /// # Parameters
    /// * `mValue`: The maximum vertical jumps.
    /// * `n`: The total steps.
    pub fn unique_paths(&self, m_value: i64, n: i64) -> i64 {
        let m = m_value as usize;
        (m + n - 1).try_into().unwrap() - m
            .iter()
            .map(|k| self.nck(m + n - k - 1, *k))
            .reduce(|a, b| a * b)
            .unwrap_or(0)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test37() {
        assert_eq!(28, UniquePaths().unique_paths(3, 7));
    }

    #[test]
    fn test595() {
        assert_eq!(557845, UniquePaths().unique_paths(59, 5));
    }

    #[test]
    fn test110() {
        assert_eq!(1, UniquePaths().unique_paths(1, 10));
    }

    #[test]
    fn testNothing() {
        assert_eq!(0, UniquePaths().unique_paths(1, 0));
    }
}
```