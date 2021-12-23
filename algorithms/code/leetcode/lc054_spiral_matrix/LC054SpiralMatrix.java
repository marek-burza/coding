package leetcode.lc054_spiral_matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * #medium
 */
public final class LC054SpiralMatrix {
    private int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public List<Integer> spiralOrder(final int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int index = 0;
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int x = 0;
        int y = 0;
        while (top <= bottom && left <= right) {
            if (x > right) {
                index = 1;
                top++;
                y = top;
                x--;
                continue;
            }
            if (y > bottom) {
                index = 2;
                right--;
                x = right;
                y--;
                continue;
            }
            if (x < left) {
                index = 3;
                bottom--;
                y = bottom;
                x++;
                continue;
            }
            if (y < top) {
                index = 0;
                left++;
                x = left;
                y++;
                continue;
            }
            list.add(matrix[y][x]);
            x += deltas[index][0];
            y += deltas[index][1];
        }
        return list;
    }
}
