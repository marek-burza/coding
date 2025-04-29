package leetcode.lc154_find_minimum_in_rotated_sorted_array_ii;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/ #hard
 */
public final class LC154FindMinimumInRotatedSortedArrayII {
    public int findMin(final int[] nums) {
        int a = 0;
        int z = nums.length - 1;
        while (a < z) {
            int m = (a + z) >>> 1;
            if (nums[z] < nums[m]) {
                a = m + 1;
            } else if (nums[m] < nums[z]) {
                z = m;
            } else {
                z--;
            }
        }
        return nums[a];
    }
}
package leetcode.lc154_find_minimum_in_rotated_sorted_array_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC154FindMinimumInRotatedSortedArrayIITests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        assertEquals(0, new LC154FindMinimumInRotatedSortedArrayII().findMin(nums));
    }

    @Test
    public void testTrickier() throws Exception {
        int[] nums = { 1, 1, 0, 1, 1, 1, 1 };
        assertEquals(0, new LC154FindMinimumInRotatedSortedArrayII().findMin(nums));
    }
}
```rust
use std::ops::BitAnd;

pub struct LC154FindMinimumInRotatedSortedArrayII {
    nums: Vec<i32>,
}

impl LC154FindMinimumInRotatedSortedArrayII {
    pub fn new(nums: Vec<i32>) -> Self {
        Self { nums }
    }

    pub fn find_min(&self) -> i32 {
        let mut a = 0;
        let mut z = self.nums.len() - 1;
        while a < z {
            let m = (a + z) >> 1;
            if self.nums[z] < self.nums[m] {
                a = m + 1;
            } else if self.nums[m] < self.nums[z] {
                z = m;
            } else {
                z -= 1;
            }
        }
        self.nums[a]
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums = vec![4, 5, 6, 7, 0, 1, 2];
        assert_eq!(LC154FindMinimumInRotatedSortedArrayII::new(nums).find_min(), 0);
    }

    #[test]
    fn test_trickier() {
        let nums = vec![1, 1, 0, 1, 1, 1, 1];
        assert_eq!(LC154FindMinimumInRotatedSortedArrayII::new(nums).find_min(), 0);
    }
}
```