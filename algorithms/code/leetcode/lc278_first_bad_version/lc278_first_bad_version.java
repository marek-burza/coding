package leetcode.lc278_first_bad_version;

/**
 * https://leetcode.com/problems/first-bad-version/ #easy
 */
public final class LC278FirstBadVersion {
    public int badVersion = 0;

    public boolean isBadVersion(final int version) {
        return badVersion <= version;
    }

    public int firstBadVersion(final int n) {
        int a = 1;
        int z = n;
        while (a != z) {
            int i = (int) (((long) a + (long) z) >>> 1);
            if (!isBadVersion(i)) {
                a = i + 1;
            } else {
                z = i;
            }
        }
        return a;
    }
}
package leetcode.lc278_first_bad_version;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC278FirstBadVersionTests {
    private void generic(final int version, final int badVersion) {
        LC278FirstBadVersion solution = new LC278FirstBadVersion();
        solution.badVersion = badVersion;
        assertEquals(badVersion, solution.firstBadVersion(version));
    }

    @Test
    public void testExample() {
        generic(8000, 456);
    }

    @Test
    public void testBigExample() {
        generic(2126753390, 1702766719);
    }

    @Test
    public void testSmallExample() {
        generic(1, 1);
    }
}
```rust
// Define a struct to hold the bad version number and methods
struct LC278FirstBadVersion {
    bad_version: i32,
}

impl LC278FirstBadVersion {
    // Create an instance with a given bad version number
    fn new(bad_version: i32) -> Self {
        LC278FirstBadVersion { bad_version }
    }

    // Check if a version is bad
    fn is_bad_version(&self, version: i32) -> bool {
        self.bad_version <= version
    }

    // Find the first bad version for a given number of versions
    fn first_bad_version(&self, n: i32) -> i32 {
        let mut low = 1;
        let mut high = n;

        while low != high {
            let mid = (low + high) / 2.into();
            if !self.is_bad_version(mid) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        low
    }
}

// Define a struct to test the LC278FirstBadVersion
struct LC278FirstBadVersionTests {}

impl LC278FirstBadVersionTests {
    fn generic(&self, version: i32, bad_version: i32) {
        let solution = LC278FirstBadVersion::new(bad_version);
        assert_eq!(bad_version, solution.first_bad_version(version));
    }
}

// Define tests for the LC278FirstBadVersion
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        LC278FirstBadVersionTests::generic(8000, 456);
    }

    #[test]
    fn test_big_example() {
        LC278FirstBadVersionTests::generic(2126753390, 1702766719);
    }

    #[test]
    fn test_small_example() {
        LC278FirstBadVersionTests::generic(1, 1);
    }
}
```