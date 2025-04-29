package leetcode.lc334_increasing_triplet_subsequence;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * #medium
 */
public final class LC334IncreasingTripletSubsequence {
    public boolean increasingTriplet(final int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int[] minBefore = new int[nums.length];
        int[] maxAfter = new int[nums.length];
        minBefore[0] = nums[0];
        maxAfter[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            minBefore[i] = Math.min(minBefore[i - 1], nums[i - 1]);
            maxAfter[nums.length - 1 - i] = Math.max(maxAfter[nums.length - i], nums[nums.length - i]);
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (minBefore[i] < nums[i] && nums[i] < maxAfter[i]) {
                return true;
            }
        }
        return false;
    }
}
package leetcode.lc334_increasing_triplet_subsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC334IncreasingTripletSubsequenceTests {
    @Test
    public void testEmpty() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] {}));
    }

    @Test
    public void testExample1() throws Exception {
        assertTrue(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 1, 2, 3, 4, 5 }));
    }

    @Test
    public void testExample2() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 5, 4, 3, 2, 1 }));
    }

    @Test
    public void testOther() throws Exception {
        assertTrue(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 1, 2, 3, 1, 2, 1 }));
    }

    @Test
    public void testNothing() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(null));
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 0, 1 }));
    }

    @Test
    public void test516() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 5, 1, 6 }));
    }

    @Test
    public void test24Minus2Minus3() throws Exception {
        assertFalse(new LC334IncreasingTripletSubsequence().increasingTriplet(new int[] { 2, 4, -2, -3 }));
    }
}
```rust
use std::iter;

struct LC334IncreasingTripletSubsequence;

impl LC334IncreasingTripletSubsequence {
    pub fn increasing_triplet(nums: &[i32]) -> bool {
        if nums.is_empty() || nums.len() < 3 {
            return false;
        }
        let mut min_before = vec![nums[0]];
        let mut max_after = vec![nums[nums.len() - 1]];
        
        for i in 1..nums.len() {
            let val = nums[i];
            min_before.push(std::cmp::min(val, min_before[i - 1]));
            max_after.push(std::cmp::max(val, max_after[nums.len() - i - 1]));
        }
        
        for i in 1..nums.len() - 1 {
            if min_before[i] < nums[i] && nums[i] < max_after[i] {
                return true;
            }
        }
        false
    }

}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(&[]));
    }

    #[test]
    fn test_example1() {
        assert!(LC334IncreasingTripletSubsequence::increasing_triplet(&[1, 2, 3, 4, 5]));
    }

    #[test]
    fn test_example2() {
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(&[5, 4, 3, 2, 1]));
    }

    #[test]
    fn test_other() {
        assert!(LC334IncreasingTripletSubsequence::increasing_triplet(&[1, 2, 3, 1, 2, 1]));
    }

    #[test]
    fn test_nothing() {
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(null));
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(&[0, 1]));
    }

    #[test]
    fn test516() {
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(&[5, 1, 6]));
    }

    #[test]
    fn test24_minus2_minus3() {
        assert!(!LC334IncreasingTripletSubsequence::increasing_triplet(&[2, 4, -2, -3]));
    }
}
```