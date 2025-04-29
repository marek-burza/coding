package leetcode.lc137_single_number_ii;

/**
 * https://leetcode.com/problems/single-number-ii/
 * #medium
 */
public final class LC137SingleNumberII {
    public int singleNumber(final int[] nums) {
        int[] counters = new int[32];
        for (int num : nums) {
            for (int i = 0; i < counters.length; i++, num >>= 1) {
                counters[i] += num & 1;
            }
        }
        int result = 0;
        for (int i = 0, mask = 1; i < counters.length; i++, mask <<= 1) {
            if (counters[i] % 3 != 0) {
                result |= mask;
            }
        }
        return result;
    }
}
package leetcode.lc137_single_number_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC137SingleNumberIITests {
    @Test
    public void test1112() throws Exception {
        int[] nums = { 1, 1, 1, 2 };
        assertEquals(2, new LC137SingleNumberII().singleNumber(nums));
    }

    @Test
    public void test4344533() throws Exception {
        int[] nums = { 4, 3, 4, 4, 5, 3, 3 };
        assertEquals(5, new LC137SingleNumberII().singleNumber(nums));
    }
}
```rust
#![allow(dead_code)]

pub struct LC137SingleNumberII {
    pub single_number(&[i32], &mut [i32]) -> i32 {
        let mut counters: Vec<i32> = vec![0; 32];
        for num in nums.iter() {
            let mask = 1 << num.trailing_zeros();
            for (i, counter) in counters.iter_mut().enumerate() {
                *counter += (*counter >> 1) | ((num & mask) ^ 1) != 0;
            }
        }
        let mut result: i32 = 0;
        let mut mask: i32 = 1 << 31;
        for (i, counter) in counters.iter().enumerate() {
            if *counter % 3 != 0 { // note: % operator is unsigned
                result |= mask >> i;
            }
        }
        result
    }

    #[test]
    fn test1112() -> Result<(), Box<dyn std::error::Error>> {
        let nums = vec![1, 1, 1, 2];
        let result = LC137SingleNumberII { }.single_number(&nums, &mut Vec::<i32>::new());
        assert_eq!(result, 2);
        Ok(())
    }

    #[test]
    fn test4344533() -> Result<(), Box<dyn std::error::Error>> {
        let nums = vec![4, 3, 4, 4, 5, 3, 3];
        let result = LC137SingleNumberII { }.single_number(&nums, &mut Vec::<i32>::new());
        assert_eq!(result, 5);
        Ok(())
    }
}
```