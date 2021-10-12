package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/rotate-image/
 * #medium
 */
public class LC048RotateImage {
    public final class Solution {
        public void rotate(int[][] matrix) {
            for (int row = 0; row < (matrix.length / 2) + (matrix.length & 1); row++) {
                for (int column = row; column < matrix.length - 1 - row; column++) {
                    int exchange = matrix[row][column];
                    matrix[row][column] = matrix[matrix.length - 1 - column][row];
                    matrix[matrix.length - 1 - column][row] = matrix[matrix.length - 1 - row][matrix.length - 1 - column];
                    matrix[matrix.length - 1 - row][matrix.length - 1 - column] = matrix[column][matrix.length - 1 - row];
                    matrix[column][matrix.length - 1 - row] = exchange;
                }
            }
        }
    }

    private void test_matrices(int[][] expected, int[][] result) {
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertEquals(expected[row].length, result[row].length);
            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(expected[row][col], result[row][col]);
            }
        }
    }

    @Test
    public void test_even() throws Exception {
        int[][] matrix = {{0, 1}, {2, 3}};
        int[][] expected = {{2, 0}, {3, 1}};
        new Solution().rotate(matrix);
        test_matrices(expected, matrix);
    }

    @Test
    public void test_odd() throws Exception {
        int[][] matrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        int[][] expected = {{6, 3, 0}, {7, 4, 1}, {8, 5, 2}};
        new Solution().rotate(matrix);
        test_matrices(expected, matrix);
    }
}
