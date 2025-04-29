package leetcode.lc136_single_number;

/**
 * https://leetcode.com/problems/single-number/ #easy
 */
public final class LC136SingleNumber {
    public int singleNumber(final int[] nums) {
        int result = 0;
        for (int value : nums) {
            result ^= value;
        }
        return result;
    }
}
package leetcode.lc136_single_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC136SingleNumberTests {
    @Test
    public void test1() throws Exception {
        assertEquals(1, new LC136SingleNumber().singleNumber(new int[] { 1 }));
    }

    @Test
    public void test1And2And1() throws Exception {
        assertEquals(2, new LC136SingleNumber().singleNumber(new int[] { 1, 2, 1 }));
    }
}
```rust
// leetcode/lc136_single_number.rs
pub struct LC136SingleNumber;

impl LC136SingleNumber {
    /// https://leetcode.com/problems/single-number/#easy
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut result = 0;
        for value in nums {
            result ^= value;
        }
        result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1() {
        assert_eq!(LC136SingleNumber::single_number(vec![1]), 1);
    }

    #[test]
    fn test1_and_2_and_1() {
        assert_eq!(LC136SingleNumber::single_number(vec![1, 2, 1]), 2);
    }
}
```