package leetcode.lc338_counting_bits;

/**
 * https://leetcode.com/problems/counting-bits/ #easy
 */
public final class LC338CountingBits {
    public int[] countBits(final int num) {
        int[] result = new int[num + 1];
        int threshold = 1;
        for (int i = 0; i < result.length; i++) {
            if ((threshold << 1) <= i) {
                threshold <<= 1;
            }
            if (i == 0) {
                result[0] = 0;
            } else {
                result[i] = 1 + result[i - threshold];
            }
        }
        return result;
    }
}
package leetcode.lc338_counting_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC338CountingBitsTests {
    @Test
    public void test2() throws Exception {
        assertArrayEquals(new int[] { 0, 1, 1 }, new LC338CountingBits().countBits(2));
    }

    @Test
    public void test5() throws Exception {
        assertArrayEquals(new int[] { 0, 1, 1, 2, 1, 2 }, new LC338CountingBits().countBits(5));
    }
}
```rust
fn count_bits(num: i32) -> Vec<i32> {
    let mut result = vec![0; num as usize + 1];
    let threshold = 1;
    for i in 0..result.len() {
        if (threshold << 1) <= i {
            threshold <<= 1;
        }
        if i == 0 {
            result[0] = 0;
        } else {
            result[i as usize] = 1 + result[(i - threshold) as usize];
        }
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test2() {
        assert_eq!(vec![0, 1, 1], count_bits(2));
    }

    #[test]
    fn test5() {
        assert_eq!(vec![0, 1, 1, 2, 1, 2], count_bits(5));
    }
}
```