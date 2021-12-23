package leetcode.lc054_spiral_matrix;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC054SpiralMatrixTests {
    private void test(final int[] expected, final int[][] matrix) throws Exception {
        List<Integer> result = new LC054SpiralMatrix().spiralOrder(matrix);
        int[] array = new int[result.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = result.get(i);
        }
        assertArrayEquals(expected, array);
    }

    @Test
    public void test258And40Minus1() throws Exception {
        int[][] matrix = { { 2, 5, 8 }, { 4, 0, -1 } };
        int[] expected = { 2, 5, 8, -1, 0, 4 };
        test(expected, matrix);
    }

    @Test
    public void test25And840Minus1() throws Exception {
        int[][] matrix = { { 2, 5 }, { 8, 4 }, { 0, -1 } };
        int[] expected = { 2, 5, 4, -1, 0, 8 };
        test(expected, matrix);
    }

    @Test
    public void testNothing() throws Exception {
        List<Integer> result;
        result = new LC054SpiralMatrix().spiralOrder(null);
        assertArrayEquals(new int[] {}, result.stream().mapToInt(x -> x).toArray());
        result = new LC054SpiralMatrix().spiralOrder(new int[][] {});
        assertArrayEquals(new int[] {}, result.stream().mapToInt(x -> x).toArray());
    }
}
