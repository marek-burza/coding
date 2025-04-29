package leetcode.lc217_contains_duplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/ #easy
 */
public final class LC217ContainsDuplicate {
    public boolean containsDuplicate(final int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}
package leetcode.lc217_contains_duplicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC217ContainsDuplicateTests {
    @Test
    public void test057() throws Exception {
        assertFalse(new LC217ContainsDuplicate().containsDuplicate(new int[] { 0, 5, 7 }));
    }

    @Test
    public void test057510() throws Exception {
        assertTrue(new LC217ContainsDuplicate().containsDuplicate(new int[] { 0, 5, 7, 5, 10 }));
    }
}
```rust
use std::collections::HashSet;

#[derive(Debug)]
pub struct LC217ContainsDuplicate {}

impl LC217ContainsDuplicate {
    pub fn contains_duplicate(&self, nums: Vec<i32>) -> bool {
        let mut seen = HashSet::new();
        for num in nums {
            if seen.contains(&num) {
                return true;
            }
            seen.insert(num);
        }
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_57() {
        assert!(!LC217ContainsDuplicate().contains_duplicate(vec![0, 5, 7]));
    }

    #[test]
    fn test_57_10() {
        assert!(LC217ContainsDuplicate().contains_duplicate(vec![0, 5, 7, 5, 10]));
    }
}
```