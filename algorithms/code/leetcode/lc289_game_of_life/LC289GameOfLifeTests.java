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
