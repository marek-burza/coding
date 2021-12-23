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
