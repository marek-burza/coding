package leetcode.lc066_plus_one;

/**
 * https://leetcode.com/problems/plus-one/ #easy
 */
public final class LC066PlusOne {
    public int[] plusOne(final int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (carry > 0) {
            int[] bigger = new int[digits.length + 1];
            bigger[0] = carry;
            System.arraycopy(digits, 0, bigger, 1, digits.length);
            return bigger;
        }
        return digits;
    }
}
package leetcode.lc066_plus_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC066PlusOneTests {
    @Test
    public void test1And9() throws Exception {
        int[] expected = { 2, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 1, 9 }));
    }

    @Test
    public void test9And9() throws Exception {
        int[] expected = { 1, 0, 0 };
        assertArrayEquals(expected, new LC066PlusOne().plusOne(new int[] { 9, 9 }));
    }
}
```rust
fn plus_one(mut digits: Vec<i32>) -> Vec<i32> {
    // Start with carry of 1
    let mut carry = 1;
    
    // Iterate from last to first digit in the array
    for i in (0..digits.len()).rev() {
        // Add current carry and current digit, update carry and new digit
        digits[i] += carry;
        carry = digits[i] / 10;
        digits[i] %= 10;
    }
    
    // If there is still carry, append it to the beginning of array
    if carry > 0 {
        let mut bigger = vec![carry];
        bigger.extend_from_slice(&digits);
        return bigger;
    } else {
        return digits;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1_and_9() {
        let expected = vec![2, 0];
        assert_eq!(expected, plus_one(vec![1, 9]));
    }

    #[test]
    fn test_9_and_9() {
        let expected = vec![1, 0, 0];
        assert_eq!(expected, plus_one(vec![9, 9]));
    }
}
```