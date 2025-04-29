package leetcode.lc053_maximum_subarray;

/**
 * https://leetcode.com/problems/maximum-subarray/ #easy
 */
public final class LC053MaximumSubarray {
    public int maxSubArray(final int[] nums) {
        int sum = 0;
        int min = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = sum < min ? sum : min;
            sum += nums[i];
            int delta = sum - min;
            max = delta > max ? delta : max;
        }
        return max;
    }
}
package leetcode.lc053_maximum_subarray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC053MaximumSubarrayTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals(6, new LC053MaximumSubarray().maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(1, new LC053MaximumSubarray().maxSubArray(new int[] { 1 }));
    }

    @Test
    public void testExample3() throws Exception {
        assertEquals(23, new LC053MaximumSubarray().maxSubArray(new int[] { 5, 4, -1, 7, 8 }));
    }

    @Test
    public void testMinus2And1() throws Exception {
        assertEquals(1, new LC053MaximumSubarray().maxSubArray(new int[] { -2, 1 }));
    }

    @Test
    public void testMinus2AndMinus1() throws Exception {
        assertEquals(-1, new LC053MaximumSubarray().maxSubArray(new int[] { -2, -1 }));
    }
}
```rust
// Maximum Subarray Sum Problem
// https://leetcode.com/problems/maximum-subarray/

struct Solution;

impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let mut sum = 0;
        let mut min_sum = 0;
        let mut max_ending_here = 0;
        let mut max_so_far = i32::MIN;

        for &num in nums.iter() {
            // Choose the maximum of current number and the sum ending at previous position
            max_ending_here = num.max(sum + num);
            // Update maximum so far if needed
            max_so_far = max_ending_here.max(max_so_far);

            // Keep track of minimum sum ending at any position
            min_sum = min_sum.max(sum);
            // Update the sum ending at current position
            sum = max_ending_here - min_sum;
        }

        max_so_far
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        assert_eq!(Solution::max_sub_array(vec![−2, 1, −3, 4, −1, 2, 1, −5, 4]), 6);
    }

    #[test]
    fn test_example2() {
        assert_eq!(Solution::max_sub_array(vec![1]), 1);
    }

    #[test]
    fn test_example3() {
        assert_eq!(Solution::max_sub_array(vec![5, 4, −1, 7, 8]), 23);
    }

    #[test]
    fn test_minus2And1() {
        assert_eq!(Solution::max_sub_array(vec![-2, 1]), 1);
    }

    #[test]
    fn test_minus2AndMinus1() {
        assert_eq!(Solution::max_sub_array(vec![-2, -1]), -1);
    }
}
```