package leetcode.lc371_sum_of_two_integers;

/**
 * https://leetcode.com/problems/sum-of-two-integers/
 * #medium
 */
public final class LC371SumOfTwoIntegers {
    public int getSum(final int a, final int b) {
        int result = 0;
        int carry = 0;
        for (int mask = 1; mask != 0; mask <<= 1) {
            int am = a & mask;
            int bm = b & mask;
            result |= am ^ bm ^ carry;
            carry = (am & bm) | (bm & carry) | (am & carry);
            carry <<= 1;
        }
        return result;
    }
}
package leetcode.lc371_sum_of_two_integers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC371SumOfTwoIntegersTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(3, new LC371SumOfTwoIntegers().getSum(1, 2));
    }
}
```rust
// leetcode.lc371_sum_of_two_integers.rs
pub struct LC371SumOfTwoIntegers;

impl LC371SumOfTwoIntegers {
    pub fn get_sum(&self, a: i32, b: i32) -> i32 {
        let mut result = 0;
        let mut carry = 0;
        for mask in 1..=63 { // equivalent to (mask != 0)
            let am = a & mask;   // equivalent to am & mask
            let bm = b & mask;   // equivalent to bm & mask
            result |= am ^ bm ^ carry; // equivalent to result |= am ^ bm ^ carry
            carry = (am & bm) | (bm & carry) | (am & carry); // equivalent to carry = (am & bm) | (bm & carry) | (am & carry)
            carry <<= 1; // equivalent to carry <<= 1
        }
        result // equivalent to return result
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(3, LC371SumOfTwoIntegers::get_sum(1, 2));
    }
}
```