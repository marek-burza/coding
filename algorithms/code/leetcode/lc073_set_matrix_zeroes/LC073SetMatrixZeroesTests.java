package leetcode.lc073_set_matrix_zeroes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC073SetMatrixZeroesTests {
    public void test(final int[][] expected, final int[][] matrix) throws Exception {
        assertEquals(expected.length, matrix.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], matrix[row]);
        }
    }

    @Test
    public void testSmallerExample1() throws Exception {
        int[][] matrix = { { 1, 0 } };
        int[][] expected = { { 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testSmallerExample2() throws Exception {
        int[][] matrix = { { 0, 1 } };
        int[][] expected = { { 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testBiggerExample() throws Exception {
        int[][] matrix = { { 0, 0, 0, 5 }, { 4, 3, 1, 4 }, { 0, 1, 1, 4 }, { 1, 2, 1, 3 }, { 0, 0, 1, 1 } };
        int[][] expected = { { 0, 0, 0, 0 }, { 0, 0, 0, 4 }, { 0, 0, 0, 0 }, { 0, 0, 0, 3 }, { 0, 0, 0, 0 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }

    @Test
    public void testOther() throws Exception {
        int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] expected = { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };
        new LC073SetMatrixZeroes().setZeroes(matrix);
        test(expected, matrix);
    }
}
