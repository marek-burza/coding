package leetcode.lc036_valid_sudoku;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * #medium
 */
public final class LC036ValidSudoku {
    public boolean validate(final char[][] board, final int[][] indices, final int dx, final int dy) {
        int check = 0;
        for (int i = 0; i < indices.length; i++) {
            int[] at = indices[i];
            char spot = board[at[1] + dy][at[0] + dx];
            if (spot == '.') {
                continue;
            }
            int mask = 1 << (spot - '0');
            if ((check & mask) == 0) {
                check |= mask;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku(final char[][] board) {
        int[][] row = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 } };
        int[][] column = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 0, 8 } };
        int[][] block = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } };
        for (int i = 0; i < 9; i++) {
            if (!validate(board, row, 0, i)) {
                return false;
            }
            if (!validate(board, column, i, 0)) {
                return false;
            }
            if (!validate(board, block, 3 * (i / 3), 3 * (i % 3))) {
                return false;
            }
        }
        return true;
    }
}
