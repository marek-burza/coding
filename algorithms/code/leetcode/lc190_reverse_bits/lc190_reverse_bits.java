package leetcode.lc190_reverse_bits;

/**
 * https://leetcode.com/problems/reverse-bits/ #easy
 */
public final class LC190ReverseBits {
    public int reverseBits(final int nValue) {
        int n = nValue;
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r <<= 1;
            r |= n & 1;
            n >>= 1;
        }
        return r;
    }
}
package leetcode.lc190_reverse_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC190ReverseBitsTests {
    @Test
    public void test43261596() throws Exception {
        assertEquals(964176192, new LC190ReverseBits().reverseBits(43261596));
    }
}
```rust
/// Reverses the bits of a given integer.
///
/// # Example
///
/// ```
/// let result = reverse_bits(43261596);
/// assert_eq!(result, 964176192);
/// ```rust
pub struct ReverseBits {
    /// Reverses the bits of a given integer.
    pub fn reverse_bits(n: i32) -> i32 {
        let mut r = 0;
        for _ in 0..32 {
            r = (r << 1) | (n & 1);
            n >>= 1;
        }
        r
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_reverse_bits() {
        assert_eq!(ReverseBits::reverse_bits(43261596), 964176192);
    }
}
```