package leetcode.lc200_number_of_islands;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/number-of-islands/
 * #medium
 */
public final class LC200NumberOfIslands {
    private static class Item {
        private int x;
        private int y;

        Item(final int xValue, final int yValue) {
            x = xValue;
            y = yValue;
        }
    }

    private static final int[][] DELTAS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private boolean land(final char[][] grid, final int x, final int y) {
        return 0 <= x && x < grid.length && 0 <= y && y < grid[x].length && grid[x][y] == '1';
    }

    private boolean traverse(final char[][] grid, final int xValue, final int yValue) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(xValue, yValue));
        boolean land = false;
        while (items.size() > 0) {
            Item item = items.remove(0);
            int x = item.x;
            int y = item.y;
            boolean check = land(grid, x, y);
            if (check) {
                land = true;
                grid[x][y] = '0';
                for (int[] delta : DELTAS) {
                    int xx = x + delta[0];
                    int yy = y + delta[1];
                    items.add(new Item(xx, yy));
                }
            }
        }
        return land;
    }

    public int numIslands(final char[][] grid) {
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
package leetcode.lc200_number_of_islands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC200NumberOfIslandsTests {
    @Test
    public void testExample() throws Exception {
        char[][] grid = { { '1' } };
        assertEquals(1, new LC200NumberOfIslands().numIslands(grid));
    }

    @Test
    public void testOther() throws Exception {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };
        assertEquals(3, new LC200NumberOfIslands().numIslands(grid));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC200NumberOfIslands().numIslands(null));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] {}));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] { null }));
        assertEquals(0, new LC200NumberOfIslands().numIslands(new char[][] { {} }));
    }
}
