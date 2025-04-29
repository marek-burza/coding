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
package leetcode.lc074_search_a_2d_matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC074SearchA2DMatrixTests {
    @Test
    public void testExample1() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 50 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 3));
    }

    @Test
    public void testExample2() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testExample3() throws Exception {
        int[][] matrix = {
                { 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample4() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 0));
    }

    @Test
    public void testExample5() throws Exception {
        int[][] matrix = {
                { 1, 1 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 2));
    }

    @Test
    public void testExample6() throws Exception {
        int[][] matrix = {
                { 1 },
                { 3 }
        };
        assertTrue(new LC074SearchA2DMatrix().searchMatrix(matrix, 1));
    }

    @Test
    public void testOther() throws Exception {
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        assertFalse(new LC074SearchA2DMatrix().searchMatrix(matrix, 13));
    }
}
```rust
fn search_matrix(matrix: Vec<Vec<i32>>, target: i32) -> bool {
    // Define the boundaries for the first row
    let mut ra = 0;
    let mut rz = matrix[0].len() - 1;

    // Find the midpoint of the range in the first row
    while ra < rz {
        let rm = (ra + rz) / 2;
        if target >= matrix[0][rm] {
            ra = rm;
        } else {
            rz = rm - 1;
        }
    }

    // Check if the target exists in the first row
    let ca = 0;
    let cz = matrix[ra].len() - 1;

    // Find the column with the target value
    while ca <= cz {
        let cm = (ca + cz) / 2;
        if matrix[ra][cm] == target {
            return true;
        } else if matrix[ra][cm] < target {
            ca = cm + 1;
        } else {
            cz = cm - 1;
        }
    }

    false
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example1() {
        let matrix = vec![
            vec![1, 3, 5, 7],
            vec![10, 11, 16, 20],
            vec![23, 30, 34, 50]
        ];
        assert!(search_matrix(matrix, 3));
    }

    #[test]
    fn test_example2() {
        let matrix = vec![
            vec![1]
        ];
        assert!(search_matrix(matrix, 1));
    }

    #[test]
    fn test_example3() {
        let matrix = vec![
            vec![1]
        ];
        assert!(!search_matrix(matrix, 0));
    }

    #[test]
    fn test_example4() {
        let matrix = vec![
            vec![1, 1]
        ];
        assert!(!search_matrix(matrix, 0));
    }

    #[test]
    fn test_example5() {
        let matrix = vec![
            vec![1, 1]
        ];
        assert!(!search_matrix(matrix, 2));
    }

    #[test]
    fn test_example6() {
        let matrix = vec![
            vec![1],
            vec![3]
        ];
        assert!(search_matrix(matrix, 1));
    }

    #[test]
    fn test_other() {
        let matrix = vec![
            vec![1, 3, 5, 7],
            vec![10, 11, 16, 20],
            vec![23, 30, 34, 60]
        ];
        assert!(!search_matrix(matrix, 13));
    }
}
```