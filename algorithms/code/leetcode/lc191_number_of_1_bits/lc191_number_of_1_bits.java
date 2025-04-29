package leetcode.lc191_number_of_1_bits;

/**
 * https://leetcode.com/problems/number-of-1-bits/ #easy
 */
public final class LC191NumberOf1Bits {
    public int hammingWeight(final int nValue) {
        int n = nValue;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }
}
package leetcode.lc191_number_of_1_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC191NumberOf1BitsTests {
    @Test
    public void test11() throws Exception {
        assertEquals(3, new LC191NumberOf1Bits().hammingWeight(11));
    }
}
```rust
// leetcode/lc191/number_of_1_bits.rs

struct LC191NumberOf1Bits;

impl LC191NumberOf1Bits {
    /// Returns the number of 1 bits in a given integer.
    fn hamming_weight(&self, n_value: i32) -> i32 {
        let mut n = n_value;
        let mut count = 0;
        for _ in 0..32 {
            count += (n & 1) as i32;
            n >>= 1;
        }
        count
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test11() {
        assert_eq!(3, LC191NumberOf1Bits().hamming_weight(11));
    }
}
```