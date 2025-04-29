package leetcode.lc059_spiral_matrix_ii;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * #medium
 */
public final class LC059SpiralMatrixII {
    public int[][] generateMatrix(final int n) {
        int[] limits = { n - 1, n - 1, 0, 0 };
        int[] restrict = { 1, -1, -1, 1 };
        int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[] indices = { 0, -1 };
        int[][] matrix = new int[n][n];
        int value = 1;
        int stage = 0;
        int index = 1;
        while (/*limits[0] >= limits[2] && */limits[1] >= limits[3]) {
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
```rust
fn generate_matrix(n: i32) -> Vec<Vec<i32>> {
    // Define the limits of the matrix
    let mut limits = [n - 1, n - 1, 0, 0];

    // Define the restrictions and delta values for each stage
    let restrict = [1, -1, -1, 1];
    let delta = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];

    // Initialize the matrix and indices
    let mut matrix: Vec<Vec<i32>> = vec![vec![0; n as usize]; n as usize];
    let mut value = 1;
    let mut stage = 0;
    let mut index = 1;

    // Loop until all elements are filled
    while limits[0] >= limits[2] && limits[1] >= limits[3] {
        // Move in the current direction and fill the matrix
        loop {
            let (x, y) = (
                indices[0] + delta[stage as usize][0],
                indices[1] + delta[stage as usize][1],
            );

            if x < 0 || y < 0 || x >= n || y >= n {
                break;
            }

            matrix[x as usize][y as usize] = value;

            // Check if we've reached the end of this direction
            let (end, limit) = if stage % 4 == 0 {
                (limits[(stage + 3) % 4], limits[(stage + 1) % 4])
            } else {
                (limits[(stage + 1) % 4], limits[stage as usize])
            };

            if matrix[x as usize][y as usize] != limit {
                break;
            }
        }

        // Update the stage and index
        limits[(stage + 3) % 4] += restrict[stage];
        stage = (stage + 1) % 4;
        index = (index + 1) % 2;

        indices[0] += delta[stage as usize][0];
        indices[1] += delta[stage as usize][1];

        value += 1;
    }

    matrix
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_example() -> Result<(), Box<dyn std::error::Error>> {
        let expected = vec![
            vec![1, 2, 3],
            vec![8, 9, 4],
            vec![7, 6, 5],
        ];

        let result = generate_matrix(3);
        assert!(result.is_empty());
        for (i, row) in result.iter().enumerate() {
            for (j, val) in row.iter().enumerate() {
                assert_eq!(*val, expected[i][j]);
            }
        }

        Ok(())
    }
}
```