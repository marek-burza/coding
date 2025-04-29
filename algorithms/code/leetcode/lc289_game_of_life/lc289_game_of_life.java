package leetcode.lc289_game_of_life;

/**
 * https://leetcode.com/problems/game-of-life/
 * #medium
 */
public final class LC289GameOfLife {
    private int countAlive(final int[][] board, final int row, final int col) {
        int count = 0;
        count += (row > 0 && col > 0) ? board[row - 1][col - 1] & 1 : 0;
        count += (row > 0) ? board[row - 1][col] & 1 : 0;
        count += (row > 0 && col < board[row].length - 1) ? board[row - 1][col + 1] & 1 : 0;
        count += (col > 0) ? board[row][col - 1] & 1 : 0;
        count += (col < board[row].length - 1) ? board[row][col + 1] & 1 : 0;
        count += (row < board.length - 1 && col > 0) ? board[row + 1][col - 1] & 1 : 0;
        count += (row < board.length - 1) ? board[row + 1][col] & 1 : 0;
        count += (row < board.length - 1 && col < board[row].length - 1) ? board[row + 1][col + 1] & 1 : 0;
        return count;
    }

    public void gameOfLife(final int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int count = countAlive(board, row, col);
                int mask;
                if ((board[row][col] & 1) == 1) {
                    mask = (count < 2 || 3 < count) ? 0 : 2;
                } else {
                    mask = (count == 3) ? 2 : 0;
                }
                board[row][col] |= mask;
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] >>= 1;
            }
        }
    }
}
package leetcode.lc289_game_of_life;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC289GameOfLifeTests {
    private void test(final int[][] expected, final int[][] board) throws Exception {
        assertEquals(expected.length, board.length);
        for (int row = 0; row < board.length; row++) {
            assertEquals(expected[row].length, board[row].length);
            for (int col = 0; col < board[row].length; col++) {
                assertEquals(expected[row][col], board[row][col]);
            }
        }
    }

    @Test
    public void testEmpty() throws Exception {
        int[][] board = { {} };
        int[][] expected = { {} };
        new LC289GameOfLife().gameOfLife(board);
        test(expected, board);
    }

    @Test
    public void testExample1() throws Exception {
        int[][] board = { { 0, 1, 0 }, { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } };
        int[][] expected = { { 0, 0, 0 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 } };
        new LC289GameOfLife().gameOfLife(board);
        test(expected, board);
    }

    @Test
    public void testExample2() throws Exception {
        int[][] board = { { 1, 1 }, { 1, 0 } };
        int[][] expected = { { 1, 1 }, { 1, 1 } };
        new LC289GameOfLife().gameOfLife(board);
        test(expected, board);
    }

    @Test
    public void testOther() throws Exception {
        int[][] board = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 } };
        int[][] expected = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 0 },
                { 0, 1, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 } };
        new LC289GameOfLife().gameOfLife(board);
        test(expected, board);
    }
}
```rust
fn count_alive(board: &Vec<Vec<i32>>, row: usize, col: usize) -> i32 {
    let mut count = 0;
    if row > 0 && col > 0 {
        count += (board[row - 1][col - 1] & 1);
    }
    if row > 0 {
        count += (board[row - 1][col] & 1);
    }
    if row > 0 && col < board[row].len() - 1 {
        count += (board[row - 1][col + 1] & 1);
    }
    if col > 0 {
        count += (board[row][col - 1] & 1);
    }
    if col < board[row].len() - 1 {
        count += (board[row][col + 1] & 1);
    }
    if row < board.len() - 1 && col > 0 {
        count += (board[row + 1][col - 1] & 1);
    }
    if row < board.len() - 1 {
        count += (board[row + 1][col] & 1);
    }
    if row < board.len() - 1 && col < board[row].len() - 1 {
        count += (board[row + 1][col + 1] & 1);
    }
    count
}

fn game_of_life(board: &mut Vec<Vec<i32>>) {
    for row in 0..board.len() {
        for col in 0..board[row].len() {
            let mut count = count_alive(&board, row, col);
            let mask;
            if (board[row][col] & 1) == 1 {
                if count < 2 || count > 3 {
                    mask = 0;
                } else {
                    mask = 2;
                }
            } else {
                if count == 3 {
                    mask = 2;
                } else {
                    mask = 0;
                }
            }
            board[row][col] |= mask;
        }
    }
    for row in 0..board.len() {
        for col in 0..board[row].len() {
            *board[row].get_mut(col).unwrap() >>= 1;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_empty() {
        let mut board = vec![vec![0; 6]];
        game_of_life(&mut board);
        assert_eq!(board.len(), board.len());
        for row in 0..board.len() {
            for col in 0..board[row].len() {
                assert_eq!(board[row][col], 0);
            }
        }
    }

    #[test]
    fn test_example1() {
        let mut board = vec![
            vec![0, 1, 0],
            vec![0, 0, 1],
            vec![1, 1, 1],
            vec![0, 0, 0],
        ];
        game_of_life(&mut board);
        assert_eq!(board.len(), board.len());
        for row in 0..board.len() {
            for col in 0..board[row].len() {
                match board[row][col] & 1 {
                    0 => assert_eq!(board[row][col], 0),
                    1 => assert_eq!(board[row][col], 1),
                    _ => panic!("unknown value"),
                }
            }
        }
    }

    #[test]
    fn test_example2() {
        let mut board = vec![
            vec![1, 1],
            vec![1, 0],
        ];
        game_of_life(&mut board);
        assert_eq!(board.len(), board.len());
        for row in 0..board.len() {
            for col in 0..board[row].len() {
                match board[row][col] & 1 {
                    0 => assert_eq!(board[row][col], 0),
                    1 => assert_eq!(board[row][col], 1),
                    _ => panic!("unknown value"),
                }
            }
        }
    }

    #[test]
    fn test_example3() {
        let mut board = vec![
            vec![0, 0, 0, 0, 0],
            vec![0, 0, 0, 1, 0],
            vec![0, 1, 0, 0, 1],
            vec![0, 1, 0, 0, 1],
            vec![0, 0, 1, 0, 0],
            vec![0, 0, 0, 0, 0],
        ];
        game_of_life(&mut board);
        assert_eq!(board.len(), board.len());
        for row in 0..board.len() {
            for col in 0..board[row].len() {
                match board[row][col] & 1 {
                    0 => assert_eq!(board[row][col], 0),
                    1 => assert_eq!(board[row][col], 1),
                    _ => panic!("unknown value"),
                }
            }
        }
    }
}
```