package leetcode.lc198_house_robber;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/house-robber/
 * #medium
 */
public final class LC198HouseRobber {
    public int rob(final int[] nums, final int offset, final HashMap<Integer, Integer> maxed) {
        if (nums.length <= offset) {
            return 0;
        }
        if (maxed.containsKey(offset)) {
            return maxed.get(offset);
        } else {
            int result = nums[offset] + rob(nums, offset + 2, maxed);
            if (offset + 1 < nums.length) {
                int other = nums[offset + 1] + rob(nums, offset + 3, maxed);
                result = Math.max(result, other);
            }
            maxed.put(offset, result);
            return (result);
        }
    }

    public int rob(final int[] nums) {
        return rob(nums, 0, new HashMap<Integer, Integer>());
    }
}
package leetcode.lc198_house_robber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC198HouseRobberTests {
    @Test
    public void test664843310() throws Exception {
        int[] nums = { 6, 6, 4, 8, 4, 3, 3, 10 };
        assertEquals(27, new LC198HouseRobber().rob(nums));
    }
}
```rust
use std::collections::HashMap;

pub struct LC198HouseRobber {}

impl LC198HouseRobber {
    pub fn rob(&self, nums: &[i32], offset: usize, maxed: &mut HashMap<usize, i32>) -> i32 {
        if nums.len() <= offset {
            return 0;
        }
        if let Some(value) = maxed.get(&offset) {
            return value.clone();
        } else {
            let result = nums[offset] + self.rob(nums, offset + 2, maxed);
            if offset + 1 < nums.len() {
                let other = nums[offset + 1] + self.rob(nums, offset + 3, maxed);
                result = result.max(other);
            }
            *maxed.entry(offset).or_insert(0) = result;
            result
        }
    }

    pub fn rob(&self, nums: &[i32]) -> i32 {
        self.rob(nums, 0, &mut HashMap::new())
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_rob() {
        let mut lc198 = LC198HouseRobber {};
        let nums = [6, 6, 4, 8, 4, 3, 3, 10];
        assert_eq!(lc198.rob(&nums), 27);
    }
}
```