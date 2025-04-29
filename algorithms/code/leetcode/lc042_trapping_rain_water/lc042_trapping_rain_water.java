package leetcode.lc042_trapping_rain_water;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/trapping-rain-water/ #hard
 */
public final class LC042TrappingRainWater {
    private static class MappingComparator implements Comparator<Integer>, Serializable {
        private int[] other;

        MappingComparator(final int[] otherValue) {
            other = otherValue;
        }

        public int compare(final Integer i1, final Integer i2) {
            return other[i2] - other[i1];
        }
    }

    private int amount(final int[] height, final int from, final int to) {
        int amount = Math.min(height[from], height[to]) * (to - from - 1);
        for (int i = from + 1; i < to; i++) {
            amount -= height[i];
        }
        return amount;
    }

    public int trap(final int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        // Sort the terrain
        Integer[] sorted = new Integer[height.length];
        for (int i = 0; i < height.length; i++) {
            sorted[i] = i;
        }
        Arrays.sort(sorted, new MappingComparator(height));
        // Fill from the top
        // (pick highest and then extend "exclusion zone")
        int count = 0;
        int left = sorted[0];
        int right = sorted[0];
        for (int i : sorted) {
            if (right < i) {
                count += amount(height, right, i);
                right = i;
            }
            if (i < left) {
                count += amount(height, i, left);
                left = i;
            }
        }
        return count;
    }
}
package leetcode.lc042_trapping_rain_water;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/trapping-rain-water/ #hard
 */
public class LC042TrappingRainWaterTests {
    @Test
    public void testExample() throws Exception {
        int[] terrain = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        assertEquals(6, new LC042TrappingRainWater().trap(terrain));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC042TrappingRainWater().trap(null));
        assertEquals(0, new LC042TrappingRainWater().trap(new int[] { 0, 1 }));
    }
}
```rust
// Define a struct to compare two integers based on the height array.
#[derive(Debug)]
struct MappingComparator {
    other: [i32; height.len()],
}

impl MappingComparator {
    // Constructor to initialize the comparator with the given height array.
    fn new(other_value: &[i32]) -> Self {
        let mut cmp = MappingComparator { other: [0; other_value.len()] };
        cmp.other.clone_from_slice(other_value);
        cmp
    }

    // Compare two indices based on their corresponding heights in the 'other' array.
    fn compare(&self, i1: usize, i2: usize) -> std::cmp::Ordering {
        self.other[i2] - self.other[i1]
    }
}

// Define a function to calculate the amount of water that can be trapped between two indices.
fn amount(height: &[i32], from: usize, to: usize) -> i32 {
    let min_height = height[from].min(height[to]);
    (to - from - 1) as i32 * min_height
        + height.iter().skip(from + 1).take_while(|&&x| x <= min_height).sum::<i32>()
}

// Define a function to calculate the total amount of water that can be trapped in the terrain.
fn trap(height: &[i32]) -> i32 {
    if height.is_empty() || height.len() < 3 {
        return 0;
    }

    // Sort the indices based on their corresponding heights in the 'other' array.
    let mut sorted_indices = Vec::new();
    for i in 0..height.len() {
        sorted_indices.push(i);
    }
    sorted_indices.sort_by(|a, b| MappingComparator::new(&height).compare(b, a));

    // Initialize variables to keep track of the left and right boundaries.
    let mut left = sorted_indices[0];
    let mut right = sorted_indices[0];

    // Iterate through the sorted indices to find the trapped water areas.
    let mut count = 0;
    for i in sorted_indices {
        if right < i {
            count += amount(height, left, i);
            left = i;
        }
        if i < left {
            count += amount(height, i, right);
            right = i;
        }
    }

    count
}

// Define tests for the 'trap' function.
#[cfg(test)]
mod tests {
    use super::*;

    // Test case 1: Trapping rain water in a terrain with multiple peaks and valleys.
    #[test]
    fn test_example() -> Result<(), Box<dyn std::error::Error>> {
        let height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1];
        assert_eq!(trap(&height), 6);
        Ok(())
    }

    // Test case 2: Trapping rain water in an empty terrain.
    #[test]
    fn test_nothing() -> Result<(), Box<dyn std::error::Error>> {
        let height = &[0, 1];
        assert_eq!(trap(height), 0);
        Ok(())
    }
}
```