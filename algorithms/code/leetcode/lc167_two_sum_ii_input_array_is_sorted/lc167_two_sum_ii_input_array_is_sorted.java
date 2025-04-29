package leetcode.lc167_two_sum_ii_input_array_is_sorted;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/ #easy
 */
public final class LC167TwoSumIIInputArrayIsSorted {
    public int[] twoSum(final int[] numbers, final int target) {
        int[] indices = new int[2];
        if (numbers == null || numbers.length < 2) {
            return indices;
        }
        int a = 0;
        int z = numbers.length - 1;
        while (a < z) {
            int v = numbers[a] + numbers[z];
            if (v == target) {
                indices[0] = a + 1;
                indices[1] = z + 1;
                break;
            } else if (v > target) {
                z--;
            } else {
                a++;
            }
        }
        return indices;
    }
}
package leetcode.lc167_two_sum_ii_input_array_is_sorted;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC167TwoSumIIInputArrayIsSortedTests {
    @Test
    public void testExample() throws Exception {
        assertArrayEquals(new int[] { 1, 2 },
                new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 2, 7, 11, 15 }, 9));
    }

    @Test
    public void testOtherExample() throws Exception {
        assertArrayEquals(new int[] { 0, 0 },
                new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 1, 5, 6, 9 }, 9));
    }

    @Test
    public void testNothing() throws Exception {
        assertArrayEquals(new int[] { 0, 0 }, new LC167TwoSumIIInputArrayIsSorted().twoSum(null, 0));
        assertArrayEquals(new int[] { 0, 0 }, new LC167TwoSumIIInputArrayIsSorted().twoSum(new int[] { 0 }, 0));
    }
}
```rust
// src/main.rs
pub struct LC167TwoSumIIInputArrayIsSorted;

impl LC167TwoSumIIInputArrayIsSorted {
    pub fn two_sum(&self, numbers: &[i32], target: i32) -> Vec<usize> {
        let mut indices = vec![0; 2];
        if numbers.is_none() || numbers.len() < 2 {
            return indices;
        }
        let a = 0;
        let z = numbers.len() - 1;
        while a < z {
            let v = numbers[a] + numbers[z];
            if v == target {
                indices[0] += 1;
                indices[1] += 1;
                break;
            } else if v > target {
                z -= 1;
            } else {
                a += 1;
            }
        }
        indices
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let solution = LC167TwoSumIIInputArrayIsSorted;
        let result = solution.two_sum(&[2, 7, 11, 15], 9);
        assert_eq!(result, vec![1, 2]);
    }

    #[test]
    fn test_other_example() {
        let solution = LC167TwoSumIIInputArrayIsSorted;
        let result = solution.two_sum(&[1, 5, 6, 9], 9);
        assert_eq!(result, vec![0, 3]);
    }

    #[test]
    fn test_nothing() {
        let solution = LC167TwoSumIIInputArrayIsSorted;
        let result = solution.two_sum(None, 0);
        assert_eq!(result, vec![0, 0]);

        let result = solution.two_sum(&[0], 0);
        assert_eq!(result, vec![0, 1]);
    }
}
```