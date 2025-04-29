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
```rust
use std::collections::VecDeque;

#[derive(Debug)]
struct Item {
    x: i32,
    y: i32,
}

impl Item {
    fn new(x_value: i32, y_value: i32) -> Self {
        Item { x: x_value, y: y_value }
    }
}

const DELTAS: [[i32; 2]; 4] = [
    [0, 1],
    [1, 0],
    [0, -1],
    [-1, 0]
];

fn land(grid: &Vec<Vec<char>>, x: i32, y: i32) -> bool {
    (x as usize >= 0 && x as usize < grid.len() && 
     y as usize >= 0 && y as usize < grid[x as usize].len()) && grid[x as usize][y as usize] == '1'
}

fn traverse(grid: &Vec<Vec<char>>, x_value: i32, y_value: i32) -> bool {
    let mut queue = VecDeque::new();
    queue.push_back(Item::new(x_value, y_value));
    let mut land = false;
    while !queue.is_empty() {
        let item = queue.pop_front().unwrap();
        let x = item.x;
        let y = item.y;
        if land(grid, x, y) && !land {
            land = true;
            grid[x as usize][y as usize] = '0';
            for delta in DELTAS.iter() {
                let xx = x + delta[0];
                let yy = y + delta[1];
                queue.push_back(Item::new(xx, yy));
            }
        }
    }
    land
}

fn num_islands(grid: Vec<Vec<char>>) -> i32 {
    if grid.is_empty() {
        return 0;
    }
    let mut count = 0;
    for x in 0..grid.len() {
        for y in 0..grid[x].len() {
            if traverse(&grid, x as i32, y as i32) {
                count += 1;
            }
        }
    }
    count
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() {
        let grid = vec![vec!['1'; 1]];
        assert_eq!(num_islands(grid), 1);
    }

    #[test]
    fn test_other() {
        let grid = vec![
            vec!['1', '1', '0', '0', '0'],
            vec!['1', '1', '0', '0', '0'],
            vec!['0', '0', '1', '0', '0'],
            vec!['0', '0', '0', '1', '1']
        ];
        assert_eq!(num_islands(grid), 3);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(num_islands(vec![vec![]]), 0);
        assert_eq!(num_islands(vec![null, null]), 0);
        assert_eq!(num_islands(vec![vec![null]]), 0);
        assert_eq!(num_islands(vec![null]), 0);
    }
}
```