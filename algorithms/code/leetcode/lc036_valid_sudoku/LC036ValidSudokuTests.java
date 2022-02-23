package leetcode.lc036_valid_sudoku;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC036ValidSudokuTests {
    private static final char[][] EXAMPLE_BOARD = {
            { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
            { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
            { '9', '8', '.', '.', '.', '.', '.', '6', '.' },
            { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
            { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
            { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
            { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
            { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
            { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    @Test
    public void testExample() throws Exception {
        assertTrue(new LC036ValidSudoku().isValidSudoku(EXAMPLE_BOARD));
    }

    @Test
    public void testOtherExample() throws Exception {
        char[][] board = {
                { '.', '8', '7', '6', '5', '4', '3', '2', '1' },
                { '2', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '3', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '4', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '5', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '6', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '7', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '8', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '9', '.', '.', '.', '.', '.', '.', '.', '.' }
        };
        assertTrue(new LC036ValidSudoku().isValidSudoku(board));
    }

    @Test
    public void testAnother1() throws Exception {
        char[][] board = Arrays.stream(EXAMPLE_BOARD).map(char[]::clone).toArray(char[][]::new);
        board[2][0] = '.';
        board[2][1] = '9';
        board[2][2] = '8';
        assertTrue(new LC036ValidSudoku().isValidSudoku(board));
    }

    @Test
    public void testAnother2() throws Exception {
        char[][] board = {
                { '.', '.', '4', '.', '.', '.', '6', '3', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '5', '.', '.', '.', '.', '.', '.', '9', '.' },
                { '.', '.', '.', '5', '6', '.', '.', '.', '.' },
                { '4', '.', '3', '.', '.', '.', '.', '.', '1' },
                { '.', '.', '.', '7', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '5', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        };
        assertFalse(new LC036ValidSudoku().isValidSudoku(board));
    }

    @Test
    public void testAnother3() throws Exception {
        char[][] board = {
                { '.', '.', '.', '.', '5', '.', '.', '1', '.' },
                { '.', '4', '.', '3', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '3', '.', '.', '1' },
                { '8', '.', '.', '.', '.', '.', '.', '2', '.' },
                { '.', '.', '2', '.', '7', '.', '.', '.', '.' },
                { '.', '1', '5', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '2', '.', '.', '.' },
                { '.', '2', '.', '9', '.', '.', '.', '.', '.' },
                { '.', '.', '4', '.', '.', '.', '.', '.', '.' }
        };
        assertFalse(new LC036ValidSudoku().isValidSudoku(board));
    }

    private static final char[] BLANK = {
            '.', '.', '.', '.', '.', '.', '.', '.', '.'
    };

    @Test
    public void testAnother4() throws Exception {
        char[][] board = {
                { '7', '.', '.', '.', '4', '.', '.', '.', '.' },
                { '.', '.', '.', '8', '6', '5', '.', '.', '.' },
                { '.', '1', '.', '2', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '9', '.', '.', '.' },
                { '.', '.', '.', '.', '5', '.', '5', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '2', '.', '.' },
                BLANK,
                BLANK
        };
        assertFalse(new LC036ValidSudoku().isValidSudoku(board));
    }
}
