package leetcode.lc075_sort_colors;

/**
 * https://leetcode.com/problems/sort-colors/
 * #medium
 */
public final class LC075SortColors {
    public void sortColors(final int[] nums) {
        int[] counters = { 0, 0, 0 };
        for (int value : nums) {
            counters[value]++;
        }
        for (int i = 0, j = 0; i < counters.length; i++) {
            for (int k = 0; k < counters[i]; k++, j++) {
                nums[j] = i;
            }
        }
    }
}
package leetcode.lc075_sort_colors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC075SortColorsTests {
    @Test
    public void test2() throws Exception {
        int[] nums = { 2 };
        int[] expected = { 2 };
        new LC075SortColors().sortColors(nums);
        assertArrayEquals(expected, nums);
    }

    @Test
    public void test10() throws Exception {
        int[] nums = { 1, 0 };
        int[] expected = { 0, 1 };
        new LC075SortColors().sortColors(nums);
        assertArrayEquals(expected, nums);
    }
}
```rust
#[derive(Debug)]
struct LC075SortColors {}

impl LC075SortColors {
    fn sort_colors(nums: &mut [i32]) {
        let mut counters = vec![0; 3];
        for num in nums {
            counters[*num as usize] += 1;
        }
        let mut j = 0;
        for i in 0..counters.len() {
            for _ in 0..counters[i] {
                nums[j] = i as i32;
                j += 1;
            }
        }
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn test2() {
            let mut nums = vec![2];
            LC075SortColors::sort_colors(&mut nums);
            assert_eq!(nums, vec![2]);
        }

        #[test]
        fn test10() {
            let mut nums = vec![1, 0];
            LC075SortColors::sort_colors(&mut nums);
            assert_eq!(nums, vec![0, 1]);
        }
    }
}
```