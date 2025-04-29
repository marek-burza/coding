package leetcode.lc268_missing_number;

/**
 * https://leetcode.com/problems/missing-number/ #easy
 */
public final class LC268MissingNumber {
    public int missingNumber(final int[] nums) {
        long expected = nums.length * (nums.length + 1) / 2;
        long sum = 0;
        for (int value : nums) {
            sum += value;
        }
        return (int) (expected - sum);
    }
}
package leetcode.lc268_missing_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC268MissingNumberTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(2, new LC268MissingNumber().missingNumber(new int[] { 0, 1, 3 }));
    }
}
```rust
// leetcode/lc268_missing_number.rs

pub struct LC268MissingNumber;

impl LC268MissingNumber {
    /// Returns the missing number in the given array.
    ///
    /// The function calculates the expected sum of numbers from 0 to n using the formula `n * (n + 1) / 2`.
    /// Then, it subtracts the actual sum of elements in the input array from the expected sum.
    pub fn missing_number(nums: &[i32]) -> i32 {
        let length = nums.len() as i64;
        let mut sum = 0;

        // Calculate the sum of numbers from 0 to n
        for num in nums {
            sum += *num;
        }

        // Calculate the expected sum using the formula
        (length * (length + 1) / 2 - sum) as i32
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(LC268MissingNumber::missing_number(&[0, 1, 3]), 2);
    }
}
```