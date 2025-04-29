package leetcode.lc050_pow_x_n;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/powx-n/
 * #medium
 */
public final class LC050PowXN {
    public double myPow(final double x, final int n) {
        if (n == 0) {
            return 1.0;
        }
        long count = n < 0 ? -(long) n : (long) n;
        double result = x;
        long power = 1;
        List<Double> powers = new LinkedList<>();
        while ((power << 1) <= count) {
            powers.add(result);
            result *= result;
            power <<= 1;
        }
        long previous = power >> 1;
        for (int i = powers.size() - 1; 0 <= i; i--) {
            long repeat = (count - power) / previous;
            double value = powers.get(i);
            for (int j = 0; j < repeat; j++) {
                result *= value;
            }
            power += repeat * previous;
            previous >>= 1;
        }
        result = n < 0 ? 1.0 / result : result;
        return result;
    }
}
package leetcode.lc050_pow_x_n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC050PowXNTests {
    @Test
    public void testSmaller() throws Exception {
        double x = 34.00515;
        int n = -3;
        double expected = Math.pow(x, n);
        assertEquals(expected, new LC050PowXN().myPow(x, n), 0);
    }

    @Test
    public void testBigger() throws Exception {
        double x = 0.00001;
        int n = 2147483647;
        double expected = Math.pow(x, n);
        assertEquals(expected, new LC050PowXN().myPow(x, n), 0);
    }

    @Test
    public void test0() throws Exception {
        assertEquals(1.0, new LC050PowXN().myPow(0, 0), 0);
    }
}
```rust
fn my_pow(mut x: f64, mut n: i32) -> f64 {
    if n == 0 {
        return 1.0;
    }

    let count = if n < 0 { -(n as u64) } else { n as u64 };
    let mut result = x;
    let mut power = 1;

    while (power << 1) <= count {
        powers.push(result);
        result *= result;
        power <<= 1;
    }

    let previous = power >> 1;
    for i in (0..powers.len()).rev() {
        let repeat = if n < 0 { -(count - power as u64) } else { (count - power as u64) };
        let value = powers[i];
        for _ in 0..repeat {
            result *= value;
        }
        power += repeat * previous;
        previous >>= 1;
    }

    if n < 0 { return 1.0 / result; } else { return result; }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_smaller() -> Result<(), Box<dyn std::error::Error>> {
        let x = 34.00515;
        let n = -3;
        let expected = f64::pow(x, n);
        assert_eq!(expected, my_pow(x, n), |e, e| format!("Expected {}, but got {}", e, e));
        Ok(())
    }

    #[test]
    fn test_bigger() -> Result<(), Box<dyn std::error::Error>> {
        let x = 0.00001;
        let n = 2147483647;
        let expected = f64::pow(x, n);
        assert_eq!(expected, my_pow(x, n), |e, e| format!("Expected {}, but got {}", e, e));
        Ok(())
    }

    #[test]
    fn test_0() -> Result<(), Box<dyn std::error::Error>> {
        assert_eq!(my_pow(0.0, 0), 1.0);
        Ok(())
    }
}
```