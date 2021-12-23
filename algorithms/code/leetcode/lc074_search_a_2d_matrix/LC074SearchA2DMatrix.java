package leetcode.lc074_search_a_2d_matrix;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * #medium
 */
public final class LC074SearchA2DMatrix {
    public boolean searchMatrix(final int[][] matrix, final int target) {
        int ra = 0;
        int rz = matrix.length - 1;
        while (ra < rz) {
            int rm = 1 + (ra + rz) / 2;
            if (target >= matrix[rm][0]) {
                ra = rm;
            } else {
                rz = rm - 1;
            }
        }
        // if (rz < 0) {
        //     return false;
        // }
        int ca = 0;
        int cz = matrix[ra].length - 1;
        while (ca < cz) {
            int cm = 1 + (ca + cz) / 2;
            if (target == matrix[ra][cm]) {
                return true;
            }
            if (target > matrix[ra][cm]) {
                ca = cm + 1;
            } else {
                cz = cm - 1;
            }
        }
        return ca == cz && target == matrix[ra][ca];
    }
}
