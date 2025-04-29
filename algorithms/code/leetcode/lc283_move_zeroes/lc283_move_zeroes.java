package leetcode.lc283_move_zeroes;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/ #easy
 */
public final class LC283MoveZeroes {
    public void moveZeroes(final int[] nums) {
        int target = 0;
        for (int index = 0; index < nums.length; index++) {
            nums[target] = nums[index];
            if (nums[index] != 0) {
                target++;
            }
        }
        Arrays.fill(nums, target, nums.length, 0);
    }
}
package leetcode.lc283_move_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC283MoveZeroesTests {
    @Test
    public void testExample() throws Exception {
        int[] nums = { 0, 1, 0, 3, 12 };
        new LC283MoveZeroes().moveZeroes(nums);
        int[] expected = { 1, 3, 12, 0, 0 };
        assertArrayEquals(expected, nums);
    }
}
```rust
fn move_zeroes(nums: &mut [i32]) {
    let mut target = 0;
    for index in 0..nums.len() {
        nums[target] = nums[index];
        if nums[index] != 0 {
            target += 1;
        }
    }
    for i in (target + 1)..nums.len() {
        nums[i] = 0;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let mut nums = [0, 1, 0, 3, 12];
        move_zeroes(&mut nums);
        let expected = [1, 3, 12, 0, 0];
        assert_eq!(nums, expected);
    }
}
```