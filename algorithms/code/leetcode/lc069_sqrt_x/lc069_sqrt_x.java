package leetcode.lc069_sqrt_x;

/**
 * https://leetcode.com/problems/sqrtx/ #easy
 */
public final class LC069SqrtX {
    public int mySqrt(final int x) {
        long a = 0;
        long z = x;
        while (a + 1 < z) {
            long m = (a + z) / 2;
            long mm = m * m;
            if (mm == x) {
                return (int) m;
            }
            if (mm < x) {
                a = m;
            } else {
                z = m - 1;
            }
        }
        if (z * z <= x) {
            return (int) z;
        }
        return (int) a;
    }
}
package leetcode.lc069_sqrt_x;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC069SqrtXTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(4));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(8));
    }

    @Test
    public void test64() throws Exception {
        assertEquals(8, new LC069SqrtX().mySqrt(64));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(2));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(1));
    }
}
```rust
// sqrt_x.rs
pub struct LC069SqrtX;

impl LC069SqrtX {
    /// Returns the integer square root of x.
    pub fn my_sqrt(&self, x: i32) -> i32 {
        let mut a = 0;
        let mut z = x as u64; // Note: In Rust we use u64 for unsigned integers
        while a + 1 < z {
            let m = (a + z) / 2;
            let mm = m * m;
            if mm == x as u64 { // Cast i32 to u64
                return m as i32; // Cast u64 back to i32
            }
            if mm < x as u64 {
                a = m;
            } else {
                z = (m - 1) as u64; // Cast i32 to u64
            }
        }
        if z * z <= x as u64 { // Cast i32 to u64
            return z as i32;
        }
        a as i32 // Cast u64 back to i32
    }
}

// tests.rs
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        assert_eq!(2, LC069SqrtX().my_sqrt(4));
    }

    #[test]
    fn test_example2() {
        assert_eq!(2, LC069SqrtX().my_sqrt(8));
    }

    #[test]
    fn test_64() {
        assert_eq!(8, LC069SqrtX().my_sqrt(64));
    }

    #[test]
    fn test_2() {
        assert_eq!(1, LC069SqrtX().my_sqrt(2));
    }

    #[test]
    fn test_1() {
        assert_eq!(1, LC069SqrtX().my_sqrt(1));
    }
}
```