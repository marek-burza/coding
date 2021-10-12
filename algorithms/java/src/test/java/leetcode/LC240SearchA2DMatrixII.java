package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * #medium
 */
public final class LC240SearchA2DMatrixII {
    public final class Solution {
        public boolean searchMatrix(final int[][] matrix, final int target, final int rowA, final int rowZ, final int colA, final int colZ) {
            if (rowA == rowZ && colA == colZ) return matrix[rowZ][colZ] == target;
            if (target < matrix[rowA][colA] || matrix[rowZ][colZ] < target) return false;
            int rowM = (rowA + rowZ) / 2;
            int colM = (colA + colZ) / 2;
            boolean cols = colA < colZ;
            boolean rows = rowA < rowZ;
            if (target <= matrix[rowM][colM]) {
                if (searchMatrix(matrix, target, rowA, rowM, colA, colM)) return true;
            }
            if (cols && target <= matrix[rowM][colZ]) {
                if (searchMatrix(matrix, target, rowA, rowM, colM + 1, colZ)) return true;
            }
            if (rows && target <= matrix[rowZ][colM]) {
                if (searchMatrix(matrix, target, rowM + 1, rowZ, colA, colM)) return true;
            }
            return !(rows & cols) ? false : searchMatrix(matrix, target, rowM + 1, rowZ, colM + 1, colZ);
        }

        public boolean searchMatrix(final int[][] matrix, final int target) {
            return searchMatrix(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
        }
    }

    @Test
    public void test_example_1() throws Exception {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        assertEquals(true, new Solution().searchMatrix(matrix, 5));
    }

    @Test
    public void test_example_2() throws Exception {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        assertEquals(false, new Solution().searchMatrix(matrix, 20));
    }

    @Test
    public void test_other_1() throws Exception {
        int[][] matrix = {{1, 4}, {2, 5}};
        assertEquals(true, new Solution().searchMatrix(matrix, 2));
    }

    @Test
    public void test_other_2() throws Exception {
        int[][] matrix = {{-1, 3}};
        assertEquals(false, new Solution().searchMatrix(matrix, 1));
    }

    @Test
    public void test_other_3() throws Exception {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        assertEquals(true, new Solution().searchMatrix(matrix, 5));
    }
}
