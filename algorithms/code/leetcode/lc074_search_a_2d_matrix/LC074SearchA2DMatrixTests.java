package leetcode.lc074_search_a_2d_matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC074SearchA2DMatrixTests {
    @Test
    public void testExample1() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 50 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 3));
    }

    @Test
    public void testExample2() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testExample3() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample4() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample5() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 2));
    }

    @Test
    public void testExample6() throws Exception {
        int[][] matrix = {
                { 1 },
                { 3 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testOther() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 13));
    }
}
