package leetcode.lc169_majority_element;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/majority-element/ #easy
 */
public final class LC169MajorityElement {
    public int majorityElement(final int[] nums) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int value : nums) {
            Integer count = frequencies.get(value);
            if (count == null) {
                count = 0;
            }
            frequencies.put(value, count + 1);
        }
        int result = Integer.MIN_VALUE;
        int count = Integer.MIN_VALUE;
        for (Entry<Integer, Integer> entry : frequencies.entrySet()) {
            int value = entry.getKey();
            int other = entry.getValue();
            if (count <= other) {
                result = value;
                count = other;
            }
        }
        return result;
    }
}
package leetcode.lc169_majority_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC169MajorityElementTests {
    @Test
    public void test12315161() throws Exception {
        int[] nums = { 1, 2, 3, 1, 5, 1, 6, 1 };
        assertEquals(1, new LC169MajorityElement().majorityElement(nums));
    }
}
```rust
// Note: Rust doesn't have a direct equivalent to Java's HashMap.
// We can use the built-in std::collections::HashMap instead.

use std::collections::{HashMap, HashSet};

/**
 * https://leetcode.com/problems/majority-element/ #easy
 */
pub struct LC169MajorityElement {
    // The algorithm to find majority element doesn't require any data member.
}

impl LC169MajorityElement {
    pub fn majority_element(&self, nums: &[i32]) -> i32 {
        let mut frequencies = HashMap::new();
        for &value in nums {
            *frequencies.entry(value).or_insert(0) += 1;
        }

        let mut result = i32::MIN;
        let mut count = i32::MIN;
        for (&value, &other) in frequencies.iter() {
            if count <= other {
                result = value;
                count = other;
            }
        }

        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test12315161() {
        let nums = vec![1, 2, 3, 1, 5, 1, 6, 1];
        assert_eq!(LC169MajorityElement::majority_element(&nums), 1);
    }
}
```