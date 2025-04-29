package leetcode.lc080_remove_duplicates_from_sorted_array_ii;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * #medium
 */
public final class LC080RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(final int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }
}
package leetcode.lc080_remove_duplicates_from_sorted_array_ii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC080RemoveDuplicatesFromSortedArrayIITests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int[] expected = { 1, 1, 2, 2, 3 };
        assertEquals(5, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test111133() throws Exception {
        int[] nums = { 1, 1, 1, 1, 3, 3 };
        int[] expected = { 1, 1, 3, 3 };
        assertEquals(4, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test11() throws Exception {
        int[] nums = { 1, 1 };
        int[] expected = { 1, 1 };
        assertEquals(2, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }

    @Test
    public void test122() throws Exception {
        int[] nums = { 1, 2, 2 };
        int[] expected = { 1, 2, 2 };
        assertEquals(3, new LC080RemoveDuplicatesFromSortedArrayII().removeDuplicates(nums));
        assertArrayEquals(expected, Arrays.copyOf(nums, expected.length));
    }
}
```rust
fn remove_duplicates(mut nums: Vec<i32>) -> usize {
    let mut i = 0;
    for n in nums.iter() {
        if i < 2 || *n > &nums[i - 2] {
            nums[i] = *n;
            i += 1;
        }
    }
    i
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums = vec![1, 1, 1, 2, 2, 3];
        let expected = vec![1, 1, 2, 2, 3];
        assert_eq!(remove_duplicates(nums), 5);
        assert_eq!(&nums[..expected.len()], &expected[..]);
    }

    #[test]
    fn test_111133() {
        let mut nums = vec![1, 1, 1, 1, 3, 3];
        let expected = vec![1, 1, 3, 3];
        assert_eq!(remove_duplicates(nums), 4);
        assert_eq!(&nums[..expected.len()], &expected[..]);
    }

    #[test]
    fn test_11() {
        let mut nums = vec![1, 1];
        let expected = vec![1, 1];
        assert_eq!(remove_duplicates(nums), 2);
        assert_eq!(&nums[..expected.len()], &expected[..]);
    }

    #[test]
    fn test_122() {
        let mut nums = vec![1, 2, 2];
        let expected = vec![1, 2, 2];
        assert_eq!(remove_duplicates(nums), 3);
        assert_eq!(&nums[..expected.len()], &expected[..]);
    }
}
```