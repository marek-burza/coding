package leetcode.lc081_search_in_rotated_sorted_array_ii;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * #medium
 */
public final class LC081SearchInRotatedSortedArrayII {
    public boolean search(final int[] nums, final int target) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                boolean ante = Arrays.binarySearch(nums, 0, i, target) >= 0;
                boolean post = Arrays.binarySearch(nums, i, nums.length, target) >= 0;
                return ante || post;
            }
        }
        return Arrays.binarySearch(nums, 0, nums.length, target) >= 0;
    }
}
package leetcode.lc081_search_in_rotated_sorted_array_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC081SearchInRotatedSortedArrayIITests {
    @Test
    public void testSimpleExample() {
        int[] nums = { 4, 5, 6, 6, 7, 0, 1, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 1));
    }

    @Test
    public void testTrickyExample() {
        int[] nums = { 1, 1, 3, 1, 1, 1, 1, 1 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 2));
    }

    @Test
    public void testOneAndOne() {
        int[] nums = { 1 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 1));
    }

    @Test
    public void testOneAndZero() {
        int[] nums = { 1 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 0));
    }

    @Test
    public void test2560012And3() {
        int[] nums = { 2, 5, 6, 0, 0, 1, 2 };
        assertFalse(new LC081SearchInRotatedSortedArrayII().search(nums, 3));
    }

    @Test
    public void test2560012And0() {
        int[] nums = { 2, 5, 6, 0, 0, 1, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 0));
    }

    @Test
    public void test2223222And3() {
        int[] nums = { 2, 2, 2, 3, 2, 2, 2 };
        assertTrue(new LC081SearchInRotatedSortedArrayII().search(nums, 3));
    }
}
```rust
fn search(nums: &Vec<i32>, target: i32) -> bool {
    for i in 1..nums.len() {
        if nums[i - 1] > nums[i] {
            let ante = match nums.binary_search(&target, 0..i) {
                Ok(_) => true,
                Err(_) => false
            };
            let post = match nums.binary_search(&target, i..nums.len()) {
                Ok(_) => true,
                Err(_) => false
            };
            return ante || post;
        }
    }
    match nums.binary_search(&target, 0..) {
        Ok(_) => true,
        Err(_) => false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_simple_example() {
        let nums = vec![4, 5, 6, 6, 7, 0, 1, 2];
        assert!(search(&nums, 1));
    }

    #[test]
    fn test_tricky_example() {
        let nums = vec![1, 1, 3, 1, 1, 1, 1, 1];
        assert!(!search(&nums, 2));
    }

    #[test]
    fn test_one_and_one() {
        let nums = vec![1];
        assert!(search(&nums, 1));
    }

    #[test]
    fn test_one_and_zero() {
        let nums = vec![1];
        assert!(!search(&nums, 0));
    }

    #[test]
    fn test2560012_and3() {
        let nums = vec![2, 5, 6, 0, 0, 1, 2];
        assert!(!search(&nums, 3));
    }

    #[test]
    fn test2560012_and0() {
        let nums = vec![2, 5, 6, 0, 0, 1, 2];
        assert!(search(&nums, 0));
    }

    #[test]
    fn test2223222_and3() {
        let nums = vec![2, 2, 2, 3, 2, 2, 2];
        assert!(search(&nums, 3));
    }
}
```