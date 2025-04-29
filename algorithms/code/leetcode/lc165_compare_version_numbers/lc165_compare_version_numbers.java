package leetcode.lc165_compare_version_numbers;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 * #medium
 */
public final class LC165CompareVersionNumbers {
    public int compareVersion(final String version1, final String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");
        for (int i = 0; i < Math.max(parts1.length, parts2.length); i++) {
            int level1 = 0;
            if (i < parts1.length) {
                level1 = Integer.parseInt(parts1[i]);
            }
            int level2 = 0;
            if (i < parts2.length) {
                level2 = Integer.parseInt(parts2[i]);
            }
            if (level1 < level2) {
                return -1;
            }
            if (level1 > level2) {
                return 1;
            }
        }
        return 0;
    }
}
package leetcode.lc165_compare_version_numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC165CompareVersionNumbersTests {
    @Test
    public void test1And1() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1"));
    }

    @Test
    public void test1And1Point0() throws Exception {
        assertEquals(0, new LC165CompareVersionNumbers().compareVersion("1", "1.0"));
    }

    @Test
    public void test2And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("2", "1"));
    }

    @Test
    public void test1And13Point1() throws Exception {
        assertEquals(-1, new LC165CompareVersionNumbers().compareVersion("1", "13.1"));
    }

    @Test
    public void test1Point0Point1And1() throws Exception {
        assertEquals(1, new LC165CompareVersionNumbers().compareVersion("1.0.1", "1"));
    }
}
```rust
#[derive(Debug)]
pub struct LC165CompareVersionNumbers;

impl LC165CompareVersionNumbers {
    pub fn compare_version(&self, version1: &str, version2: &str) -> i32 {
        let parts1 = version1.split('.').map(|s| s.parse().unwrap()).collect::<Vec<i32>>();
        let parts2 = version2.split('.').map(|s| s.parse().unwrap()).collect::<Vec<i32>>();

        for (part1, part2) in parts1.into_iter().zip(parts2.into_iter()) {
            if part1 < part2 {
                return -1;
            } else if part1 > part2 {
                return 1;
            }
        }

        if parts1.len() < parts2.len() {
            return -1;
        } else if parts1.len() > parts2.len() {
            return 1;
        }

        0
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1_and_1() {
        assert_eq!(LC165CompareVersionNumbers::compare_version("1", "1"), 0);
    }

    #[test]
    fn test1_and_1_point_0() {
        assert_eq!(LC165CompareVersionNumbers::compare_version("1", "1.0"), 0);
    }

    #[test]
    fn test2_and_1() {
        assert_eq!(LC165CompareVersionNumbers::compare_version("2", "1"), 1);
    }

    #[test]
    fn test1_and_13_point_1() {
        assert_eq!(LC165CompareVersionNumbers::compare_version("1", "13.1"), -1);
    }

    #[test]
    fn test1_point_0_point_1_and_1() {
        assert_eq!(LC165CompareVersionNumbers::compare_version("1.0.1", "1"), 1);
    }
}
```