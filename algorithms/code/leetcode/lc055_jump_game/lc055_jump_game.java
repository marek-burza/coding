package leetcode.lc055_jump_game;

/**
 * https://leetcode.com/problems/jump-game/
 * #medium
 */
public final class LC055JumpGame {
    public boolean canJump(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int front = 0;
        for (int i = 0; /*i <= front*/; i++) {
            if (front >= nums.length - 1) {
                return true;
            }
            if (i == front && nums[front] == 0) {
                return false;
            }
            if (front < i + nums[i]) {
                front = i + nums[i];
            }
        }
        // return false;
    }
}
package leetcode.lc055_jump_game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC055JumpGameTests {
    @Test
    public void test25002Integers() throws Exception {
        int[] nums1 = new int[25003];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = 25000 - i;
        }
        nums1[25000] = 1;
        nums1[25001] = 0;
        nums1[25002] = 0;
        assertFalse(new LC055JumpGame().canJump(nums1));
    }

    @Test
    public void test123() throws Exception {
        int[] nums2 = { 1, 2, 3 };
        assertTrue(new LC055JumpGame().canJump(nums2));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC055JumpGame().canJump(null));
        assertFalse(new LC055JumpGame().canJump(new int[] {}));
        assertTrue(new LC055JumpGame().canJump(new int[] { 0 }));
    }
}
```rust
#[derive(Debug, PartialEq)]
pub struct LC055JumpGame {
    nums: Vec<i32>,
}

impl LC055JumpGame {
    pub fn new(nums: Vec<i32>) -> Self {
        if nums.is_empty() {
            return LC055JumpGame { nums };
        }
        if nums.len() == 1 {
            return LC055JumpGame { nums };
        }
        LC055JumpGame { nums }
    }

    /// https://leetcode.com/problems/jump-game/
    pub fn can_jump(&self) -> bool {
        let mut front = 0;
        for i in 0..self.nums.len() {
            if front >= self.nums.len() - 1 {
                return true;
            }
            if i == front && self.nums[front] == 0 {
                return false;
            }
            if front < i + self.nums[i as usize] {
                front = i + self.nums[i as usize];
            }
        }
        // todo: return false
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test25002Integers() -> Result<(), Box<dyn std::error::Error>> {
        let mut nums = Vec::<i32>::with_capacity(25003);
        for i in 0..=25000 {
            nums.push((i * -1) as i32);
        }
        nums[25000] = 1;
        nums[25001] = 0;
        nums[25002] = 0;

        let lc055JumpGame = LC055JumpGame::new(nums);

        assert!(lc055JumpGame.can_jump());

        Ok(())
    }

    #[test]
    fn test123() -> Result<(), Box<dyn std::error::Error>> {
        let mut nums = Vec::<i32>::with_capacity(3);
        for i in 0..nums.len() {
            nums.push(i + 1) as i32;
        }
        let lc055JumpGame = LC055JumpGame::new(nums);

        assert!(lc055JumpGame.can_jump());

        Ok(())
    }

    #[test]
    fn testNothing() -> Result<(), Box<dyn std::error::Error>> {
        let mut lc055JumpGame = LC055JumpGame::new(Vec::<i32>::with_capacity(0));
        assert!(!lc055JumpGame.can_jump());
        assert!(lc055JumpGame.can_jump(Vec::<i32>::with_capacity(1)));

        Ok(())
    }
}
```