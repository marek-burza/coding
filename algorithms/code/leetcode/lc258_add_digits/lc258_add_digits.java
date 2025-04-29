package leetcode.lc258_add_digits;

/**
 * https://leetcode.com/problems/add-digits/ #easy
 */
public final class LC258AddDigits {
    public int addDigits(final int numValue) {
        int num = numValue;
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                int digit = num % 10;
                sum += digit;
                num /= 10;
            }
            num = sum;
        }
        return num;
        // return (int) (num - 9 * Math.floor((num - 1) / 9));
    }
}
package leetcode.lc258_add_digits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC258AddDigitsTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(2, new LC258AddDigits().addDigits(38));
    }
}
```rust
// https://leetcode.com/problems/add-digits/#easy

fn add_digits(num_value: i32) -> i32 {
    // Calculate the sum of digits in num_value
    let mut sum = 0;
    while num_value > 0 {
        let digit = num_value % 10;
        sum += digit;
        num_value /= 10;
    }
    
    // Repeat process until sum is less than 10
    if sum >= 10 {
        add_digits(sum)
    } else {
        sum
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(2, add_digits(38));
    }
}
```