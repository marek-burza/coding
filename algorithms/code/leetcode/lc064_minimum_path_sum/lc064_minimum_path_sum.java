package leetcode.lc064_minimum_path_sum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * #medium
 */
public final class LC064MinimumPathSum {
    public int minPathSum(final int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] sums = new int[grid.length][grid[0].length];
        for (int[] row : sums) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        sums[0][0] = grid[0][0];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 });
        while (queue.size() != 0) {
            int[] at = queue.poll();
            if (!visited[at[0]][at[1]]) {
                visited[at[0]][at[1]] = true;
                if (at[0] + 1 < grid.length) {
                    int[] right = { at[0] + 1, at[1] };
                    queue.add(right);
                    int sum = sums[at[0]][at[1]] + grid[right[0]][right[1]];
                    if (sum < sums[right[0]][right[1]]) {
                        sums[right[0]][right[1]] = sum;
                    }
                }
                if (at[1] + 1 < grid[0].length) {
                    int[] down = { at[0], at[1] + 1 };
                    queue.add(down);
                    int sum = sums[at[0]][at[1]] + grid[down[0]][down[1]];
                    // if (sum < sums[down[0]][down[1]]) {
                    sums[down[0]][down[1]] = sum;
                    // }
                }
            }
        }
        return sums[sums.length - 1][sums[sums.length - 1].length - 1];
    }
}
package leetcode.lc064_minimum_path_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC064MinimumPathSumTests {
    @Test
    public void testExample() throws Exception {
        int[][] grid = {
                { 1, 1, 2, 2 },
                { 2, 1, 2, 2 },
                { 2, 1, 1, 2 },
                { 2, 2, 1, 1 }
        };
        assertEquals(7, new LC064MinimumPathSum().minPathSum(grid));
    }
}
```rust
fn min_path_sum(grid: Vec<Vec<i32>>) -> i32 {
    let rows = grid.len();
    let cols = grid[0].len();
    let mut visited = vec![vec![false; cols]; rows];
    let mut sums = vec![vec![i32::MAX; cols]; rows];

    // Initialize first element of sums array
    sums[0][0] = grid[0][0];
    visited[0][0] = true;

    // Create queue and add first element
    let mut queue = Vec::new();
    queue.push((0, 0));

    while !queue.is_empty() {
        // Dequeue the next element
        let (at_x, at_y) = queue.pop().unwrap();

        if !visited[at_x][at_y] {
            visited[at_x][at_y] = true;

            if at_x + 1 < rows {
                let right = (at_x + 1, at_y);
                queue.push(right);

                // Update the sums array
                let sum = sums[at_x][at_y] + grid[right[0]][right[1]];
                if sum < sums[right[0]][right[1]] {
                    sums[right[0]][right[1]] = sum;
                }
            }

            if at_y + 1 < cols {
                let down = (at_x, at_y + 1);
                queue.push(down);

                // Update the sums array
                let sum = sums[at_x][at_y] + grid[down[0]][down[1]];
                if sum < sums[down[0]][down[1]] {
                    sums[down[0]][down[1]] = sum;
                }
            }
        }
    }

    // Return the minimum path sum
    sums[rows - 1][cols - 1]
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let grid = vec![
            vec![1, 1, 2, 2],
            vec![2, 1, 2, 2],
            vec![2, 1, 1, 2],
            vec![2, 2, 1, 1]
        ];

        assert_eq!(min_path_sum(grid), 7);
    }
}
```