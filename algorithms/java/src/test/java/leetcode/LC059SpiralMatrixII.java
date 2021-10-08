package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * #medium
 */
public class LC059SpiralMatrixII {
    public final class Solution {
        public int[][] generateMatrix(int n) {
            int[] limits = {n - 1, n - 1, 0, 0};
            int[] restrict = {1, -1, -1, 1};
            int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int[] indices = {0, -1};
            int[][] matrix = new int[n][n];
            int value = 1;
            int stage = 0;
            int index = 1;
            while (limits[0] >= limits[2] && limits[1] >= limits[3]) {
                do {
                    indices[0] += delta[stage][0];
                    indices[1] += delta[stage][1];
                    matrix[indices[0]][indices[1]] = value++;
                } while (indices[index] != limits[stage]);
                limits[(stage + 3) % 4] += restrict[stage];
                stage = (stage + 1) % 4;
                index = (index + 1) % 2;
            }
            return matrix;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[][] expected = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        int[][] result = new Solution().generateMatrix(3);
        assertNotEquals(null, result);
        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], result[i]);
        }
    }
}
