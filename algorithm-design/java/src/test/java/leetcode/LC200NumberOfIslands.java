package leetcode;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/number-of-islands/
 */
public class LC200NumberOfIslands {
    public class Solution {
        private int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        private boolean land(char[][] grid, int x, int y) {
            return 0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] == '1';
        }

        private boolean traverse(char[][] grid, int x, int y) {
            ArrayList<Integer> xs = new ArrayList<>();
            ArrayList<Integer> ys = new ArrayList<>();
            xs.add(x);
            ys.add(y);
            boolean land = false;
            while (xs.size() > 0 && ys.size() > 0) {
                x = xs.remove(0);
                y = ys.remove(0);
                boolean check = land(grid, x, y);
                if (check) {
                    land = true;
                    grid[x][y] = '0';
                    for (int[] delta : deltas) {
                        int xx = x + delta[0];
                        int yy = y + delta[1];
                        xs.add(xx);
                        ys.add(yy);
                    }
                }
            }
            return land;
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int count = 0;
            for (int x = 0; x < grid.length; x++) {
                if (grid[x] == null) {
                    return 0;
                }
                for (int y = 0; y < grid[x].length; y++) {
                    if (traverse(grid, x, y)) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    @Test
    public void test_example() throws Exception {
        char[][] grid = {{'1'}};
        assertEquals(1, new Solution().numIslands(grid));
    }
}
