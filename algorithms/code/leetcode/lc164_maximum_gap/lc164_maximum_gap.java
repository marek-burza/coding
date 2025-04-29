package leetcode.lc164_maximum_gap;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-gap/ #hard
 */
public final class LC164MaximumGap {
    public int maximumGap(final int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int maxE = Arrays.stream(nums).max().getAsInt();
        int minE = Arrays.stream(nums).min().getAsInt();
        double len = (double) (maxE - minE) / (double) (n - 1);
        int[] maxA = new int[n];
        Arrays.fill(maxA, Integer.MIN_VALUE);
        int[] minA = new int[n];
        Arrays.fill(minA, Integer.MAX_VALUE);
        for (int num : nums) {
            int index = (int) ((num - minE) / len);
            maxA[index] = Math.max(maxA[index], num);
            minA[index] = Math.min(minA[index], num);
        }
        int gap = 0;
        int prev = maxA[0];
        for (int i = 1; i < n; i++) {
            if (minA[i] == Integer.MAX_VALUE) {
                continue;
            }
            gap = Math.max(gap, minA[i] - prev);
            prev = maxA[i];
        }
        return gap;
        // Pigeon hole principle:
        // We keep the biggest and smallest pigeon fitting in the hole
        // and that's enough to find the gap in linear way
    }
}
package leetcode.lc164_maximum_gap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC164MaximumGapTests {
    @Test
    public void test33And2and100And70() throws Exception {
        int[] nums1 = { 33, 2, 100, 70 };
        assertEquals(37, new LC164MaximumGap().maximumGap(nums1));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC164MaximumGap().maximumGap(null));
        assertEquals(0, new LC164MaximumGap().maximumGap(new int[0]));
    }
}
```rust
fn maximum_gap(nums: Vec<i32>) -> i32 {
    if nums.is_empty() {
        return 0;
    }

    let max_e = *nums.iter().max().unwrap();
    let min_e = *nums.iter().min().unwrap();
    let len = (max_e - min_e) as f64 / (nums.len() as f64 - 1.0);

    let mut max_a = vec![i32::MIN; nums.len()];
    let mut min_a = vec![i32::MAX; nums.len()];

    for num in nums {
        let index = (num - min_e) as usize / len as usize;
        max_a[index] = max_a[index].max(num);
        min_a[index] = min_a[index].min(num);
    }

    let mut gap = 0;
    let mut prev = max_a[0];

    for i in 1..nums.len() {
        if min_a[i] == i32::MAX {
            continue;
        }
        gap = gap.max(min_a[i] - prev);
        prev = max_a[i];
    }

    gap
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_33_and_2_and_100_and_70() -> Result<(), Box<dyn std::error::Error>> {
        let nums1 = vec![33, 2, 100, 70];
        assert_eq!(maximum_gap(nums1), 37);
        Ok(())
    }

    #[test]
    fn test_nothing() -> Result<(), Box<dyn std::error::Error>> {
        assert_eq!(maximum_gap(vec![]), 0);
        assert_eq!(maximum_gap(Vec::new()), 0);

        Ok(())
    }
}
```