package leetcode.lc240_search_a_2d_matrix_ii;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * #medium
 */
public final class LC240SearchA2DMatrixII {
    public boolean searchMatrix(final int[][] matrix, final int target, final int rowA, final int rowZ, final int colA,
            final int colZ) {
        if (rowA == rowZ && colA == colZ) {
            return matrix[rowZ][colZ] == target;
        }
        // if (target < matrix[rowA][colA] || matrix[rowZ][colZ] < target) {
        // return false;
        // }
        int rowM = (rowA + rowZ) >>> 1;
        int colM = (colA + colZ) >>> 1;
        boolean cols = colA < colZ;
        boolean rows = rowA < rowZ;
        if (target <= matrix[rowM][colM]) {
            if (searchMatrix(matrix, target, rowA, rowM, colA, colM)) {
                return true;
            }
        }
        if (cols && target <= matrix[rowM][colZ]) {
            if (searchMatrix(matrix, target, rowA, rowM, colM + 1, colZ)) {
                return true;
            }
        }
        if (rows && target <= matrix[rowZ][colM]) {
            if (searchMatrix(matrix, target, rowM + 1, rowZ, colA, colM)) {
                return true;
            }
        }
        return !(rows && cols) ? false : searchMatrix(matrix, target, rowM + 1, rowZ, colM + 1, colZ);
    }

    public boolean searchMatrix(final int[][] matrix, final int target) {
        return searchMatrix(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }
}
