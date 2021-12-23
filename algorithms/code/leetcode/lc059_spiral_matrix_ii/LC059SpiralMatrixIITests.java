package leetcode.lc059_spiral_matrix_ii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class LC059SpiralMatrixIITests {
    @Test
    public void testExample() throws Exception {
        int[][] expected = { { 1, 2, 3 }, { 8, 9, 4 }, { 7, 6, 5 } };
        int[][] result = new LC059SpiralMatrixII().generateMatrix(3);
        assertNotEquals(null, result);
        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }
}
