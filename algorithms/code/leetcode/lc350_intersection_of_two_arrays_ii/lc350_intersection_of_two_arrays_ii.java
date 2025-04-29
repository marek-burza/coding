package leetcode.lc350_intersection_of_two_arrays_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/ #easy
 */
public final class LC350IntersectionOfTwoArraysII {
    public int[] intersect(final int[] nums1, final int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> found = new ArrayList<>();
        for (int i1 = 0, i2 = 0; i1 < nums1.length && i2 < nums2.length;) {
            if (nums1[i1] < nums2[i2]) {
                i1++;
                continue;
            }
            if (nums1[i1] > nums2[i2]) {
                i2++;
                continue;
            }
            found.add(nums1[i1]);
            i1++;
            i2++;
        }
        int[] result = new int[found.size()];
        for (int index = 0; index < result.length; index++) {
            result[index] = found.get(index);
        }
        return result;
    }
}
package leetcode.lc350_intersection_of_two_arrays_ii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC350IntersectionOfTwoArraysIITests {
    @Test
    public void testExample() throws Exception {
        int[] expected = { 2, 2 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testExampleFlipped() throws Exception {
        int[] expected = { 2, 2 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 2, 2 }, new int[] { 1, 2, 2, 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test1And1() throws Exception {
        int[] expected = { 1 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1 }, new int[] { 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test12And11() throws Exception {
        int[] expected = { 1 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 1, 2 }, new int[] { 1, 1 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }

    @Test
    public void test495And94984() throws Exception {
        int[] expected = { 4, 5, 9 };
        int[] result = new LC350IntersectionOfTwoArraysII().intersect(new int[] { 4, 9, 5 },
                new int[] { 9, 4, 9, 8, 5 });
        Arrays.sort(result);
        assertArrayEquals(expected, result);
    }
}
```rust
use std::collections::{HashSet, VecDeque};

pub struct LC350IntersectionOfTwoArraysII {
}

impl LC350IntersectionOfTwoArraysII {
    pub fn intersect(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut set1 = HashSet::new();
        for num in nums1.iter() {
            set1.insert(*num);
        }
        
        let mut set2 = HashSet::new();
        for num in nums2.iter() {
            set2.insert(*num);
        }
        
        let mut found: Vec<i32> = Vec::new();
        for &num in set1.iter() {
            if set2.contains(&num) {
                found.push(num);
                set2.remove(&num);
            }
        }
        return found;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let nums1 = vec![1, 2, 2, 1];
        let nums2 = vec![2, 2];
        let expected = [2, 2];
        assert_eq!(LC350IntersectionOfTwoArraysII::intersect(nums1.clone(), nums2), expected);
    }

    #[test]
    fn test_example_flipped() {
        let nums1 = vec![2, 2];
        let nums2 = vec![1, 2, 2, 1];
        let expected = [2, 2];
        assert_eq!(LC350IntersectionOfTwoArraysII::intersect(nums1, nums2), expected);
    }

    #[test]
    fn test_1_and_1() {
        let nums1 = vec![1];
        let nums2 = vec![1];
        let expected = [1];
        assert_eq!(LC350IntersectionOfTwoArraysII::intersect(nums1.clone(), nums2), expected);
    }

    #[test]
    fn test_12_and_11() {
        let nums1 = vec![1, 2];
        let nums2 = vec![1, 1];
        let expected = [1];
        assert_eq!(LC350IntersectionOfTwoArraysII::intersect(nums1.clone(), nums2), expected);
    }

    #[test]
    fn test_495_and_94984() {
        let nums1 = vec![4, 9, 5];
        let nums2 = vec![9, 4, 9, 8, 5];
        let expected = [4, 5, 9];
        assert_eq!(LC350IntersectionOfTwoArraysII::intersect(nums1.clone(), nums2), expected);
    }
}
```