package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://leetcode.com/problemset/algorithms/
 */
public class LC054SpiralMatrix {
    public class Solution {
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
    }

    private void test(int[] expected, int[][] matrix) throws Exception {
        List<Integer> result = new Solution().spiralOrder(matrix);
        int[] array = new int[result.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = result.get(i);
        }
        assertArrayEquals(expected, array);
    }

    @Test
    public void test_2_5_8__4_0_Minus1() throws Exception {
        int[][] matrix = {{2, 5, 8}, {4, 0, -1}};
        int[] expected = {2, 5, 8, -1, 0, 4};
        test(expected, matrix);
    }

    @Test
    public void test_2_5__8_4__0_Minus1() throws Exception {
        int[][] matrix = {{2, 5}, {8, 4}, {0, -1}};
        int[] expected = {2, 5, 4, -1, 0, 8};
        test(expected, matrix);
    }
}
