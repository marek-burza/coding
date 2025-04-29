package leetcode.lc088_merge_sorted_array;

/**
 * https://leetcode.com/problems/merge-sorted-array/ #easy
 */
public final class LC088MergeSortedArray {
    public void merge(final int[] nums1, final int mValue, final int[] nums2, final int nValue) {
        int m = mValue;
        int n = nValue;
        int i = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[i] = nums1[m];
                m--;
            } else {
                nums1[i] = nums2[n];
                n--;
            }
            i--;
        }
        while (n >= 0) {
            nums1[i] = nums2[n];
            n--;
            i--;
        }
    }
}
package leetcode.lc088_merge_sorted_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC088MergeSortedArrayTests {
    @Test
    public void testExample1() throws Exception {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        int[] expected = { 1, 2, 2, 3, 5, 6 };
        new LC088MergeSortedArray().merge(nums1, 3, nums2, 3);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void testExample2() throws Exception {
        int[] nums1 = { 1 };
        int[] nums2 = {};
        int[] expected = { 1 };
        new LC088MergeSortedArray().merge(nums1, 1, nums2, 0);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void testExample3() throws Exception {
        int[] nums1 = { 0 };
        int[] nums2 = { 1 };
        int[] expected = { 1 };
        new LC088MergeSortedArray().merge(nums1, 0, nums2, 1);
        assertArrayEquals(expected, nums1);
    }

    @Test
    public void test13711000And4And4620And() throws Exception {
        int[] nums1 = { 1, 3, 7, 11, 0, 0, 0 };
        int[] nums2 = { 4, 6, 20 };
        int[] expected = { 1, 3, 4, 6, 7, 11, 20 };
        new LC088MergeSortedArray().merge(nums1, 4, nums2, 3);
        assertArrayEquals(expected, nums1);
    }
}
```rust
struct Lc088MergeSortedArray;

impl Lc088MergeSortedArray {
    pub fn merge(&self, nums1: &mut [i32], m: i32, nums2: &mut [i32], n: i32) {
        let mut m = m;
        let mut n = n;
        let mut i = m + n - 1;
        m -= 1;
        n -= 1;

        while m >= 0 && n >= 0 {
            if nums1[m] > nums2[n] {
                nums1[i] = nums1[m];
                m -= 1;
            } else {
                nums1[i] = nums2[n];
                n -= 1;
            }
            i -= 1;
        }

        while n >= 0 {
            nums1[i] = nums2[n];
            n -= 1;
            i -= 1;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example_1() {
        let mut nums1: [i32; 6] = [1, 2, 3, 0, 0, 0];
        let mut nums2: [i32; 3] = [2, 5, 6];

        Lc088MergeSortedArray::merge(&mut nums1, 3, &mut nums2, 3);

        assert_eq!(&nums1[..], &[1, 2, 2, 3, 5, 6]);
    }

    #[test]
    fn test_example_2() {
        let mut nums1: [i32; 1] = [1];
        let mut nums2: [i32; 0] = [];

        Lc088MergeSortedArray::merge(&mut nums1, 1, &mut nums2, 0);

        assert_eq!(&nums1[..], &[1]);
    }

    #[test]
    fn test_example_3() {
        let mut nums1: [i32; 1] = [0];
        let mut nums2: [i32; 1] = [1];

        Lc088MergeSortedArray::merge(&mut nums1, 0, &mut nums2, 1);

        assert_eq!(&nums1[..], &[1]);
    }

    #[test]
    fn test_13711000_and_4_and_4620_and() {
        let mut nums1: [i32; 7] = [1, 3, 7, 11, 0, 0, 0];
        let mut nums2: [i32; 3] = [4, 6, 20];

        Lc088MergeSortedArray::merge(&mut nums1, 4, &mut nums2, 3);

        assert_eq!(&nums1[..], &[1, 3, 4, 6, 7, 11, 20]);
    }
}
```