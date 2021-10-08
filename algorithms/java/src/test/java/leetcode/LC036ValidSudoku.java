package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/valid-sudoku/
 * #medium
 */
public class LC036ValidSudoku {
    public final class Solution {
        public boolean validate(char[][] board, int[][] indices, int dx, int dy) {
            int check = 0;
            for (int i = 0; i < indices.length; i++) {
                int[] at = indices[i];
                char spot = board[at[1] + dy][at[0] + dx];
                if (spot == '.') continue;
                int mask = 1 << (spot - '0');
                if ((check & mask) == 0) {
                    check |= mask;
                } else {
                    return false;
                }
            }
            return true;
        }

        public boolean isValidSudoku(char[][] board) {
            int[][] row = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}};
            int[][] column = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}};
            int[][] block = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
            for (int i = 0; i < 9; i++) {
                if (!validate(board, row, 0, i)) return false;
                if (!validate(board, column, i, 0)) return false;
                if (!validate(board, block, 3 * (i / 3), 3 * (i % 3))) return false;
            }
            return true;
        }
    }

    @Test
    public void test_example() throws Exception {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'9', '8', '.', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertEquals(true, new Solution().isValidSudoku(board));
    }

    @Test
    public void test_other_example() throws Exception {
        char[][] board = {
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        assertEquals(true, new Solution().isValidSudoku(board));
    }
    //[".87654321","2........","3........","4........","5........","6........","7........","8........","9........"]
}
