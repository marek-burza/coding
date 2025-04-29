package leetcode.lc162_find_peak_element;

/**
 * https://leetcode.com/problems/find-peak-element/
 * #medium
 */
public final class LC162FindPeakElement {
    public int findPeakElement(final int[] nums) {
        for (int i = 1; i <= nums.length; i++) {
            boolean postFalling;
            postFalling = i == nums.length || nums[i - 1] > nums[i];
            if (postFalling) {
                return i - 1;
            }
        }
        return -1;
    }
}
package leetcode.lc162_find_peak_element;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC162FindPeakElementTests {
    @Test
    public void test1231() throws Exception {
        assertEquals(2, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 1 }));
    }

    @Test
    public void test1234() throws Exception {
        assertEquals(3, new LC162FindPeakElement().findPeakElement(new int[] { 1, 2, 3, 4 }));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(-1, new LC162FindPeakElement().findPeakElement(new int[] {}));
    }
}
```rust
// src/main.rs
// https://leetcode.com/problems/find-peak-element/
pub struct LC162FindPeakElement;

impl LC162FindPeakElement {
    pub fn find_peak_element(nums: &[i32]) -> i32 {
        let mut result = -1;
        for (index, &num) in nums.iter().enumerate() {
            if index == 0 || num >= nums[index - 1] {
                if index == nums.len() - 1 || num <= nums[index + 1] {
                    return index as i32;
                }
                result = index as i32;
            }
        }
        // This case should not happen according to the problem statement
        panic!("Index out of bounds");
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_1231() {
        assert_eq!(LC162FindPeakElement::find_peak_element(&[1, 2, 3, 1]), 2);
    }

    #[test]
    fn test_1234() {
        assert_eq!(LC162FindPeakElement::find_peak_element(&[1, 2, 3, 4]), 3);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(LC162FindPeakElement::find_peak_element(&[]), -1);
    }
}
```