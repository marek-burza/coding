package leetcode.lc220_contains_duplicate_iii;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * #medium
 */
public final class LC220ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(final int[] nums, final int k, final int t) {
        NavigableSet<Long> sorted = new TreeSet<>();
        Queue<Long> ordered = new LinkedList<>();
        for (int num : nums) {
            Long ceiling = sorted.ceiling((long) num);
            Long floor = sorted.floor((long) num);
            if ((ceiling != null && ceiling - num <= t) || (floor != null && num - floor <= t)) {
                return true;
            }
            ordered.add((long) num);
            sorted.add((long) num);
            if (ordered.size() > k) {
                sorted.remove(ordered.poll());
            }
        }
        return false;
    }
}
package leetcode.lc220_contains_duplicate_iii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC220ContainsDuplicateIIITests {
    @Test
    public void test110202() throws Exception {
        int[] nums = { 1, 10, 20, 2 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test110204() throws Exception {
        int[] nums = { 1, 10, 20, 4 };
        assertFalse(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test11020302() throws Exception {
        int[] nums = { 1, 10, 20, 30, 2 };
        assertFalse(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 3, 2));
    }

    @Test
    public void test8715161915And1And3() throws Exception {
        int[] nums = { 8, 7, 15, 1, 6, 1, 9, 15 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 1, 3));
    }

    @Test
    public void test21474836402147483641And1And100() throws Exception {
        int[] nums = { 2147483640, 2147483641 };
        assertTrue(new LC220ContainsDuplicateIII().containsNearbyAlmostDuplicate(nums, 1, 100));
    }
}
```rust
use std::collections::{BTreeSet, VecDeque};
use std::cmp;

pub struct LC220ContainsDuplicateIII {
    k: usize,
    t: i32,
}

impl LC220ContainsDuplicateIII {
    pub fn new(k: usize, t: i32) -> Self {
        Self { k, t }
    }

    pub fn contains_nearby_almost_duplicate(&self, nums: &[i32], k: usize, t: i32) -> bool {
        let mut sorted = BTreeSet::new();
        let mut ordered = VecDeque::new();

        for num in nums {
            if let Some(ceiling) = sorted.range((num - t)..=(num + t)).next_back() {
                if num >= *ceiling && (num as i64 - *ceiling as i64) <= self.t as i64 {
                    return true;
                }
            }

            ordered.push_back(*num);
            sorted.insert(*num);

            if ordered.len() > k {
                let first = ordered.pop_front().unwrap();
                sorted.remove(&first);
            }
        }

        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_110202() {
        let nums = [1, 10, 20, 2];
        let k = 3;
        let t = 2;

        let mut lc220_contains_duplicate_iii = LC220ContainsDuplicateIII::new(k, t);
        assert!(lc220_contains_duplicate_iii.contains_nearby_almost_duplicate(&nums[..], k, t));
    }

    #[test]
    fn test_110204() {
        let nums = [1, 10, 20, 4];
        let k = 3;
        let t = 2;

        let mut lc220_contains_duplicate_iii = LC220ContainsDuplicateIII::new(k, t);
        assert!(!lc220_contains_duplicate_iii.contains_nearby_almost_duplicate(&nums[..], k, t));
    }

    #[test]
    fn test_11020302() {
        let nums = [1, 10, 20, 30, 2];
        let k = 3;
        let t = 2;

        let mut lc220_contains_duplicate_iii = LC220ContainsDuplicateIII::new(k, t);
        assert!(!lc220_contains_duplicate_iii.contains_nearby_almost_duplicate(&nums[..], k, t));
    }

    #[test]
    fn test_8715161915And1And3() {
        let nums = [8, 7, 15, 1, 6, 1, 9, 15];
        let k = 1;
        let t = 3;

        let mut lc220_contains_duplicate_iii = LC220ContainsDuplicateIII::new(k, t);
        assert!(lc220_contains_duplicate_iii.contains_nearby_almost_duplicate(&nums[..], k, t));
    }

    #[test]
    fn test_21474836402147483641And1And100() {
        let nums = [2147483640, 2147483641];
        let k = 1;
        let t = 100;

        let mut lc220_contains_duplicate_iii = LC220ContainsDuplicateIII::new(k, t);
        assert!(lc220_contains_duplicate_iii.contains_nearby_almost_duplicate(&nums[..], k, t));
    }
}
```