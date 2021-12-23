package leetcode.lc073_set_matrix_zeroes;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * #medium
 */
public final class LC073SetMatrixZeroes {
    public void setZeroes(final int[][] matrix) {
        boolean row0 = false;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                row0 = true;
            }
        }
        boolean col0 = false;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[0][col] == 0) {
                col0 = true;
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        for (int row = 0; row0 && row < matrix.length; row++) {
            matrix[row][0] = 0;
        }
        for (int col = 0; col0 && col < matrix[0].length; col++) {
            matrix[0][col] = 0;
        }
    }
}
