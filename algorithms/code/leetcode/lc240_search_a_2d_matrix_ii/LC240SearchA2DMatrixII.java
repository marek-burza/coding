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
package leetcode.lc240_search_a_2d_matrix_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC240SearchA2DMatrixIITests {
    private static final int[][] EXAMPLE_MATRIX = {
            { 1, 4, 7, 11, 15 },
            { 2, 5, 8, 12, 19 },
            { 3, 6, 9, 16, 22 },
            { 10, 13, 14, 17, 24 },
            { 18, 21, 23, 26, 30 }
    };

    @Test
    public void testExample1() throws Exception {
        assertTrue(new LC240SearchA2DMatrixII().searchMatrix(EXAMPLE_MATRIX, 5));
    }

    @Test
    public void testExample2() throws Exception {
        assertFalse(new LC240SearchA2DMatrixII().searchMatrix(EXAMPLE_MATRIX, 20));
    }

    @Test
    public void testOther1() throws Exception {
        int[][] matrix = { { 1, 4 }, { 2, 5 } };
        assertTrue(new LC240SearchA2DMatrixII().searchMatrix(matrix, 2));
    }

    @Test
    public void testOther2() throws Exception {
        int[][] matrix = { { -1, 3 } };
        assertFalse(new LC240SearchA2DMatrixII().searchMatrix(matrix, 1));
    }

    @Test
    public void testOther3() throws Exception {
        int[][] matrix = {
                { 1, 2, 3, 4, 5 },
                { 6, 7, 8, 9, 10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 }
        };
        assertTrue(new LC240SearchA2DMatrixII().searchMatrix(matrix, 5));
    }
}
