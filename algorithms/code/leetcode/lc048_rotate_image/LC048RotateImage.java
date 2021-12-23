package leetcode.lc048_rotate_image;

/**
 * https://leetcode.com/problems/rotate-image/
 * #medium
 */
public final class LC048RotateImage {
    public void rotate(final int[][] matrix) {
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
