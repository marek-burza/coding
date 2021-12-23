package leetcode.lc048_rotate_image;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC048RotateImageTests {
    private void testMatrices(final int[][] expected, final int[][] result) {
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertEquals(expected[row].length, result[row].length);
            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(expected[row][col], result[row][col]);
            }
        }
    }

    @Test
    public void testEven() throws Exception {
        int[][] matrix = { { 0, 1 }, { 2, 3 } };
        int[][] expected = { { 2, 0 }, { 3, 1 } };
        new LC048RotateImage().rotate(matrix);
        testMatrices(expected, matrix);
    }

    @Test
    public void testOdd() throws Exception {
        int[][] matrix = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        int[][] expected = { { 6, 3, 0 }, { 7, 4, 1 }, { 8, 5, 2 } };
        new LC048RotateImage().rotate(matrix);
        testMatrices(expected, matrix);
    }
}
