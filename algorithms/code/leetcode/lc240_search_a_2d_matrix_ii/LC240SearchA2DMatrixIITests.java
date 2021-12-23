package leetcode.lc240_search_a_2d_matrix_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC240SearchA2DMatrixIITests {
    @Test
    public void testExample1() throws Exception {
        int[][] matrix = {
                { 1, 4, 7, 11, 15 },
                { 2, 5, 8, 12, 19 },
                { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 }
        };
        assertTrue(new LC240SearchA2DMatrixII().searchMatrix(matrix, 5));
    }

    @Test
    public void testExample2() throws Exception {
        int[][] matrix = {
                { 1, 4, 7, 11, 15 },
                { 2, 5, 8, 12, 19 },
                { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 },
                { 18, 21, 23, 26, 30 }
        };
        assertFalse(new LC240SearchA2DMatrixII().searchMatrix(matrix, 20));
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
