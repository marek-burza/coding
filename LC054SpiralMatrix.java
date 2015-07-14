package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problemset/algorithms/
 */
public class LC054SpiralMatrix {
    private int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<Integer> spiralOrder(int[][] matrix) {
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

    public static void main(String[] arguments) {
        LC054SpiralMatrix solution = new LC054SpiralMatrix();
        int[][] matrix1 = {{2, 5, 8}, {4, 0, -1}};
        for (int i = 0; i < matrix1.length; i++) {
            System.out.println(Arrays.toString(matrix1[i]));
        }
        System.out.println(solution.spiralOrder(matrix1));
        int[][] matrix2 = {{2, 5}, {8, 4}, {0, -1}};
        for (int i = 0; i < matrix2.length; i++) {
            System.out.println(Arrays.toString(matrix2[i]));
        }
        System.out.println(solution.spiralOrder(matrix2));
    }
}
