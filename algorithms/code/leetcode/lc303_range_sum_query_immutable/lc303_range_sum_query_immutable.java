package leetcode.lc303_range_sum_query_immutable;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/ #easy
 */
public final class LC303RangeSumQueryImmutable {
    public static final class NumArray {
        private int[] sums;

        NumArray(final int[] nums) {
            sums = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                sums[i] = sum;
            }
        }

        public int sumRange(final int i, final int j) {
            int sum = 0;
            if (0 < i) {
                sum = -sums[i - 1];
            }
            sum += sums[j];
            return sum;
        }
    }
}
package leetcode.lc303_range_sum_query_immutable;

import org.junit.jupiter.api.Test;

import leetcode.lc303_range_sum_query_immutable.LC303RangeSumQueryImmutable.NumArray;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class LC303RangeSumQueryImmutableTests {
    @Test
    public void test0And2() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(1, new NumArray(nums).sumRange(0, 2));
    }

    @Test
    public void test2And5() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(-1, new NumArray(nums).sumRange(2, 5));
    }

    @Test
    public void test0And5() throws Exception {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        assertEquals(-3, new NumArray(nums).sumRange(0, 5));
    }

    @Test
    public void testCoverage() throws Exception {
        assertNotNull(new LC303RangeSumQueryImmutable());
    }
}
```rust
#[derive(Debug)]
pub struct NumArray {
    sums: Vec<i32>,
}

impl NumArray {
    pub fn new(nums: impl IntoIterator<Item = i32>) -> Self {
        let sums = nums.into_iter().scan(0, |sum, x| {
            *sum += x;
            Some(*sum)
        }).collect::<Vec<_>>();
        
        Self { sums }
    }

    pub fn sum_range(&self, i: usize, j: usize) -> i32 {
        if i > 0 {
            -self.sums[i-1]
        } else {
            0
        } +
        self.sums[j] -
        self.sums.get(i).unwrap_or(&0)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_sum_range() {
        let nums = vec![-2, 0, 3, -5, 2, -1];
        assert_eq!(NumArray::new(nums).sum_range(0, 2), 1);
        assert_eq!(NumArray::new(nums).sum_range(2, 5), -1);
        assert_eq!(NumArray::new(nums).sum_range(0, 5), -3);
    }

    #[test]
    fn test_num_array_creation() {
        let nums = vec![-2, 0, 3, -5, 2, -1];
        let _num_array = NumArray::new(nums);
    }
}
```