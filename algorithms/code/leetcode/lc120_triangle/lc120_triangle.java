package leetcode.lc120_triangle;

import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * #medium
 */
public final class LC120Triangle {
    public int minimumTotal(final List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) {
            return 0;
        }
        int height = triangle.size();
        int[] sums = new int[triangle.get(height - 1).size()];
        sums[0] = triangle.get(0).get(0);
        for (int l = 1; l < height; l++) {
            List<Integer> line = triangle.get(l);
            int n = line.size();
            for (int i = n - 1; i >= 0; i--) {
                int left = i == 0 ? Integer.MAX_VALUE : sums[i - 1];
                int right = i == n - 1 ? Integer.MAX_VALUE : sums[i];
                sums[i] = Math.min(left, right) + line.get(i);
            }
        }
        int min = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sums[i] < min) {
                min = sums[i];
            }
        }
        return min;
    }
}
package leetcode.lc120_triangle;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC120TriangleTests {
    private List<List<Integer>> construct(final int[][] compact) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int[] array : compact) {
            List<Integer> line = new ArrayList<>();
            triangle.add(line);
            for (int value : array) {
                line.add(value);
            }
        }
        return triangle;
    }

    @Test
    public void testExample() throws Exception {
        int[][] triangle = {
                { 2 },
                { 3, 4 },
                { 6, 5, 7 },
                { 4, 1, 8, 3 }
        };
        assertEquals(11, new LC120Triangle().minimumTotal(construct(triangle)));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC120Triangle().minimumTotal(null));
        assertEquals(0, new LC120Triangle().minimumTotal(new ArrayList<>()));
        List<List<Integer>> nothing = new ArrayList<>();
        nothing.add(new ArrayList<>());
        assertEquals(0, new LC120Triangle().minimumTotal(nothing));
    }
}
```rust
// Define the Triangle structure
struct Triangle {
    lines: Vec<Vec<i32>>,
}

impl Triangle {
    // Create a triangle from compact representation
    fn construct(compact: Vec<Vec<i32>>) -> Self {
        let mut triangle = Triangle { lines: Vec::new() };
        for row in compact {
            let line = row.iter().map(|&x| x).collect();
            triangle.lines.push(line);
        }
        triangle
    }

    // Calculate minimum total sum of a triangle
    fn minimum_total(&self) -> i32 {
        if self.lines.is_empty() || self.lines[0].is_empty() {
            return 0;
        }
        let height = self.lines.len();
        let mut sums: Vec<i32> = vec![0; self.lines[self.lines.len() - 1].len()];
        sums[0] = self.lines[0][0];
        for l in 1..height {
            let line = &self.lines[l];
            let n = line.len();
            for i in (0..n).rev() {
                let left = if i == 0 { i32::MAX } else { sums[i - 1] };
                let right = if i == n - 1 { i32::MAX } else { sums[i] };
                sums[i] = std::cmp::min(left, right) + line[i];
            }
        }
        *sums.iter().min().unwrap()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() -> i32 {
        let compact = vec![
            vec![2],
            vec![3, 4],
            vec![6, 5, 7],
            vec![4, 1, 8, 3],
        ];
        let triangle = Triangle::construct(compact);
        triangle.minimum_total()
    }

    #[test]
    fn test_nothing() -> i32 {
        assert_eq!(0, Triangle::minimum_total(None));
        assert_eq!(0, Triangle::minimum_total(Vec::new()));
        let nothing: Vec<Vec<i32>> = vec![];
        let triangle = Triangle { lines: nothing };
        triangle.minimum_total()
    }
}
```