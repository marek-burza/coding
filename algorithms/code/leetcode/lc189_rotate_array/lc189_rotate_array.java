package leetcode.lc189_rotate_array;

/**
 * https://leetcode.com/problems/rotate-array/
 * #medium
 */
public final class LC189RotateArray {
    private void reverse(final int[] nums, final int aValue, final int bValue) {
        int a = aValue;
        int b = bValue;
        while (a < b) {
            int swap = nums[a];
            nums[a] = nums[b];
            nums[b] = swap;
            a++;
            b--;
        }
    }

    public void rotate(final int[] nums, final int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
}
package leetcode.lc189_rotate_array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC189RotateArrayTests {
    @Test
    public void test1234567And3() throws Exception {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        new LC189RotateArray().rotate(nums, 3);
        int[] expected = { 5, 6, 7, 1, 2, 3, 4 };
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test123456And2() throws Exception {
        int[] nums = { 1, 2, 3, 4, 5, 6 };
        new LC189RotateArray().rotate(nums, 2);
        int[] expected = { 5, 6, 1, 2, 3, 4 };
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test1And2() throws Exception {
        int[] nums = { 1 };
        new LC189RotateArray().rotate(nums, 1);
        int[] expected = { 1 };
        assertArrayEquals(expected, nums);
    }
}
```rust
// Rotate Array
pub struct LC189RotateArray;

impl LC189RotateArray {
    /// Reverses the subarray from index aValue to bValue in the given array.
    fn reverse(nums: &mut [i32], aValue: i32, bValue: i32) {
        let (a, b) = (aValue as usize, bValue as usize);
        for i in 0..(b - a + 1) {
            let swap = nums[a + i];
            nums[a + i] = nums[b - i];
            nums[b - i] = swap;
        }
    }

    /// Rotates the given array by k positions.
    pub fn rotate(nums: &mut [i32], k: i32) {
        // Calculate k to be within 0 and nums.len() - 1
        let k = k % nums.len();

        // Reverse entire array
        self.reverse(nums, 0, nums.len() as i32 - 1);

        // Reverse first k elements
        self.reverse(nums, 0, k as i32 - 1);

        // Reverse remaining elements from index k to end
        self.reverse(nums, k as i32, nums.len() as i32 - 1);
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1234567_and3() {
        let mut nums = [1, 2, 3, 4, 5, 6, 7];
        LC189RotateArray::rotate(&mut nums, 3);
        assert_eq!(&nums, &[5, 6, 7, 1, 2, 3, 4]);
    }

    #[test]
    fn test_123456_and2() {
        let mut nums = [1, 2, 3, 4, 5, 6];
        LC189RotateArray::rotate(&mut nums, 2);
        assert_eq!(&nums, &[5, 6, 1, 2, 3, 4]);
    }

    #[test]
    fn test_1_and2() {
        let mut nums = [1];
        LC189RotateArray::rotate(&mut nums, 1);
        assert_eq!(&nums, &[1]);
    }
}
```