package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * #medium
 */
public class LC074SearchA2DMatrix {
    public final class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int ra = 0, rz = matrix.length - 1;
            while (ra < rz) {
                int rm = 1 + (ra + rz) / 2;
                if (target >= matrix[rm][0]) {
                    ra = rm;
                } else {
                    rz = rm - 1;
                }
            }
            if (rz < 0) return false;
            int ca = 0, cz = matrix[ra].length - 1;
            while (ca < cz && ca >= 0 && cz < matrix[ra].length) {
                int cm = 1 + (ca + cz) / 2;
                if (target == matrix[ra][cm]) return true;
                if (target > matrix[ra][cm]) {
                    ca = cm + 1;
                } else {
                    cz = cm - 1;
                }
            }
            return ca == cz && target == matrix[ra][ca];
        }
    }

    @Test
    public void test_example_1() throws Exception {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        assertEquals(true, new Solution().searchMatrix(matrix, 3));
    }

    @Test
    public void test_example_2() throws Exception {
        int[][] matrix = {
                {1}
        };
        assertEquals(true, new Solution().searchMatrix(matrix, 1));
    }

    @Test
    public void test_example_3() throws Exception {
        int[][] matrix = {
                {1}
        };
        assertEquals(false, new Solution().searchMatrix(matrix, 0));
    }

    @Test
    public void test_example_4() throws Exception {
        int[][] matrix = {
                {1, 1}
        };
        assertEquals(false, new Solution().searchMatrix(matrix, 0));
    }

    @Test
    public void test_example_5() throws Exception {
        int[][] matrix = {
                {1, 1}
        };
        assertEquals(false, new Solution().searchMatrix(matrix, 2));
    }

    @Test
    public void test_example_6() throws Exception {
        int[][] matrix = {
                {1},
                {3}
        };
        assertEquals(true, new Solution().searchMatrix(matrix, 1));
    }
}
