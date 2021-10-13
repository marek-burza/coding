package leetcode.lc289_game_of_life;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/game-of-life/
 * #medium
 */
public final class LC289GameOfLife {
    public final class Solution {
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
    public void test_empty() throws Exception {
        int[][] board = {{}};
        int[][] expected = {{}};
        new Solution().gameOfLife(board);
        test(expected, board);
    }

    @Test
    public void test_example() throws Exception {
        int[][] board = {{1, 1}, {1, 0}};
        int[][] expected = {{1, 1}, {1, 1}};
        new Solution().gameOfLife(board);
        test(expected, board);
    }
}
