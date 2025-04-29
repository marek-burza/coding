package leetcode.lc357_count_numbers_with_unique_digits;

/**
 * https://leetcode.com/problems/count-numbers-with-unique-digits/
 * #medium
 */
public final class LC357CountNumbersWithUniqueDigits {
    private int count(final String prefix, final int n) {
        if (prefix.length() == n) {
            return 1;
        }
        int sum = 1;
        String[] digits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        int first = prefix.length() == 0 ? 1 : 0;
        for (int i = first; i < digits.length; i++) {
            if (!prefix.contains(digits[i])) {
                sum += count(prefix + digits[i], n);
            }
        }
        return sum;
    }

    public int countNumbersWithUniqueDigits(final int n) {
        return count("", n);
    }
}
package leetcode.lc357_count_numbers_with_unique_digits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC357CountNumbersWithUniqueDigitsTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(91, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(2));
    }

    @Test
    public void test0() throws Exception {
        assertEquals(1, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(0));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(10, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(1));
    }
}
```rust
fn count_with_unique_digits(prefix: &str, n: i32) -> i32 {
    if prefix.len() == n as usize {
        return 1;
    }

    let mut sum = 0;
    const DIGITS: &[&str] = &["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];

    for i in prefix.chars().position(|c| !DIGITS.contains(c)) {
        if i.is_none() {
            sum += count_with_unique_digits(format!("{}{}", prefix, DIGITS[0]), n);
        } else {
            let first_digit = format!("{}", prefix.chars().nth(i.unwrap()).unwrap());
            for j in 1..10 {
                sum += count_with_unique_digits(format!("{}{}", prefix, DIGITS[j]), n);
            }
        }
    }

    sum
}

fn count_numbers_with_unique_digits(n: i32) -> i32 {
    count_with_unique_digits("", n)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        assert_eq!(count_numbers_with_unique_digits(2), 91);
    }

    #[test]
    fn test_0() {
        assert_eq!(count_numbers_with_unique_digits(0), 1);
    }

    #[test]
    fn test_1() {
        assert_eq!(count_numbers_with_unique_digits(1), 10);
    }
}
```