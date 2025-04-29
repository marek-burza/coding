package leetcode.lc260_single_number_iii;

/**
 * https://leetcode.com/problems/single-number-iii/
 * #medium
 */
public final class LC260SingleNumberIII {
    public int[] singleNumber(final int[] nums) {
        int xor = 0;
        for (int value : nums) {
            xor ^= value;
        }
        int mask = xor & (~(xor - 1));
        int[] values = { 0, 0 };
        for (int value : nums) {
            if ((value & mask) == 0) {
                values[0] ^= value;
            } else {
                values[1] ^= value;
            }
        }
        return values;
    }
}
package leetcode.lc260_single_number_iii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC260SingleNumberIIITests {
    @Test
    public void test() throws Exception {
        int[] result = new LC260SingleNumberIII().singleNumber(new int[] { 1, 2, 1, 3, 2, 5 });
        Arrays.sort(result);
        assertArrayEquals(new int[] { 3, 5 }, result);
    }
}
```rust
// leetcode::lc260_single_number_iii module
mod lc260_single_number_iii {
    /**
     * https://leetcode.com/problems/single-number-iii/
     * #medium
     */

    // LC260SingleNumberIII struct
    #[derive(Debug, Clone)]
    pub struct LC260SingleNumberIII;

    impl LC260SingleNumberIII {
        /// Returns an array containing a single number that appears once in the input array.
        ///
        /// The solution iterates through all numbers in the input array, performing XOR operations.
        /// It then uses bit masking to separate the result into two groups: numbers with set bits
        /// and numbers without set bits. The remaining element is returned as an array of size 2.
        pub fn single_number(nums: Vec<i32>) -> Vec<i32> {
            let mut xor = 0;
            for num in nums.iter() {
                xor ^= *num;
            }
            let mask = xor & !((xor ^ (xor - 1)) as u32);
            let mut values = [0, 0];
            for num in nums.iter() {
                if ((mask & (*num as u32) != 0) == false) {
                    values[0] ^= *num;
                } else {
                    values[1] ^= *num;
                }
            }
            values
        }
    }

    // LC260SingleNumberIIITests struct
    #[derive(Debug)]
    pub struct LC260SingleNumberIIITests;

    impl LC260SingleNumberIIITests {
        #[test]
        fn test() {
            let result = LC260SingleNumberIII::single_number(vec![1, 2, 1, 3, 2, 5]);
            assert_eq!(vec![3, 5], result);
        }
    }
}
```