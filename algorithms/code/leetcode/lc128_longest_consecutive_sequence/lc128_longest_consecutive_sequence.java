package leetcode.lc128_longest_consecutive_sequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * #medium
 */
public final class LC128LongestConsecutiveSequence {
    private static class Range {
        private int a;
        private int z;

        Range(final int aValue, final int zValue) {
            a = aValue;
            z = zValue;
        }
    }

    public int longestConsecutive(final int[] nums) {
        Set<Integer> seen = new HashSet<>();
        Map<Integer, Range> map = new HashMap<>();
        int length = 0;
        for (int num : nums) {
            if (seen.contains(num)) {
                continue;
            } else {
                seen.add(num);
            }
            boolean less = num > Integer.MIN_VALUE && map.containsKey(num - 1);
            boolean more = num < Integer.MAX_VALUE && map.containsKey(num + 1);
            Range ante = null;
            Range post = null;
            if (less) {
                ante = map.get(num - 1);
            }
            if (more) {
                post = map.get(num + 1);
            }
            int a = num;
            int z = num;
            if (less && more) {
                a = ante.a;
                z = post.z;
            }
            if (less) {
                a = ante.a;
            }
            if (more) {
                z = post.z;
            }
            Range range = new Range(a, z);
            map.put(a, range);
            map.put(z, range);
            int span = z - a + 1;
            if (span > length) {
                length = span;
            }
        }
        return length;
        // This can be simplified by storing only the length of the range in the hash
        // table instead of range itself
    }
}
package leetcode.lc128_longest_consecutive_sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC128LongestConsecutiveSequenceTests {
    @Test
    public void test100And4And200And1And3And2() throws Exception {
        int[] nums1 = { 100, 4, 200, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }

    @Test
    public void testLonger() throws Exception {
        int[] nums2 = { 4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2,
                -9, 1 };
        assertEquals(8, new LC128LongestConsecutiveSequence().longestConsecutive(nums2));
    }

    @Test
    public void testMax() throws Exception {
        int[] nums1 = { 100, 4, Integer.MAX_VALUE, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }

    @Test
    public void testMin() throws Exception {
        int[] nums1 = { 100, 4, Integer.MIN_VALUE, 1, 3, 2 };
        assertEquals(4, new LC128LongestConsecutiveSequence().longestConsecutive(nums1));
    }
}
```rust
fn longest_consecutive_sequence(nums: Vec<i32>) -> i32 {
    let mut seen = std::collections::HashSet::new();
    let mut map = std::collections::HashMap::new();

    for num in nums {
        if !seen.insert(num) {
            continue;
        }

        let less = num > i32::MIN && map.contains_key(&num - 1);
        let more = num < i32::MAX && map.contains_key(&num + 1);

        let ante = match less { true => Some(map.get(&(num - 1)).unwrap()), false => None };
        let post = match more { true => Some(map.get(&(num + 1)).unwrap()), false => None };

        let a = num;
        let z = num;

        if less && more {
            a = ante.unwrap().0;
            z = post.unwrap().1;
        }

        if less {
            a = ante.unwrap().0;
        }
        if more {
            z = post.unwrap().1;
        }

        map.insert(a, (a, z));
        map.insert(z, (a, z));

        let span = z - a + 1;

        if span > i32::MAX as i64 {
            continue;
        }

        if span > *map.get(&span).unwrap().0 {
            *map.get_mut(&span).unwrap() = (span, span);
        }
    }

    map.iter()
        .filter_map(|(_, (a, z))| { Some(a - z + 1) })
        .max()
        .unwrap_or(0)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test100And4And200And1And3And2() {
        assert_eq!(longest_consecutive_sequence(vec![100, 4, 200, 1, 3, 2]), 4);
    }

    #[test]
    fn testLonger() {
        assert_eq!(longest_consecutive_sequence(vec![4, 2, 2, -4, 0, -2, 4, -3, -4, -4, -5, 1, 4, -9, 5, 0, 6, -8, -1, -3, 6, 5, -8, -1, -5, -1, 2, -9, 1]), 8);
    }

    #[test]
    fn testMax() {
        assert_eq!(longest_consecutive_sequence(vec![100, 4, i32::MAX, 1, 3, 2]), 4);
    }

    #[test]
    fn testMin() {
        assert_eq!(longest_consecutive_sequence(vec![100, 4, i32::MIN, 1, 3, 2]), 4);
    }
}
```