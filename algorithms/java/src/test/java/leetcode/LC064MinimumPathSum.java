package leetcode;

import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * #medium
 */
public class LC064MinimumPathSum {
    public final class Solution {
        public int minPathSum(int[][] grid) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            int[][] sums = new int[grid.length][grid[0].length];
            for (int[] row : sums) Arrays.fill(row, Integer.MAX_VALUE);
            sums[0][0] = grid[0][0];
            Queue<int[]> queue = new LinkedList();
            queue.add(new int[]{0, 0});
            while (queue.size() != 0) {
                int[] at = queue.poll();
                if (!visited[at[0]][at[1]]) {
                    visited[at[0]][at[1]] = true;
                    if (at[0] + 1 < grid.length) {
                        int[] right = {at[0] + 1, at[1]};
                        queue.add(right);
                        int sum = sums[at[0]][at[1]] + grid[right[0]][right[1]];
                        if (sum < sums[right[0]][right[1]]) {
                            sums[right[0]][right[1]] = sum;
                        }
                    }
                    if (at[1] + 1 < grid[0].length) {
                        int[] down = {at[0], at[1] + 1};
                        queue.add(down);
                        int sum = sums[at[0]][at[1]] + grid[down[0]][down[1]];
                        if (sum < sums[down[0]][down[1]]) {
                            sums[down[0]][down[1]] = sum;
                        }
                    }
                }
            }
            return sums[sums.length - 1][sums[sums.length - 1].length - 1];
        }
    }

    @Test
    public void test_example() throws Exception {
        int[][] grid = {
                {1, 1, 2, 2},
                {2, 1, 2, 2},
                {2, 1, 1, 2},
                {2, 2, 1, 1}
        };
        assertEquals(7, new Solution().minPathSum(grid));
    }
}
