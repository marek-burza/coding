package leetcode.lc120_triangle;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/triangle/
 * #medium
 */
public final class LC120Triangle {
    public final class Solution {
        public int minimumTotal(final List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) {
                return 0;
            }
            int height = triangle.size();
            int[] sums = new int[triangle.get(height - 1).size()];
            sums[0] = triangle.get(0).get(0);
            for (int l = 1; l < height; l++) {
                List<Integer> line = triangle.get(l);
                int n = line.size();
                for (int i = n - 1; i >= 0; i--) {
                    int left = i == 0 ? Integer.MAX_VALUE : sums[i - 1];
                    int right = i == n - 1 ? Integer.MAX_VALUE : sums[i];
                    sums[i] = Math.min(left, right) + line.get(i);
                }
            }
            int min = sums[0];
            for (int i = 1; i < sums.length; i++) {
                if (sums[i] < min) {
                    min = sums[i];
                }
            }
            return min;
        }
    }

    private List<List<Integer>> construct(final int[][] compact) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int[] array : compact) {
            List<Integer> line = new ArrayList<>();
            triangle.add(line);
            for (int value : array) {
                line.add(value);
            }
        }
        return triangle;
    }

    @Test
    public void test_example() throws Exception {
        int[][] triangle = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        assertEquals(11, new Solution().minimumTotal(construct(triangle)));
    }
}
