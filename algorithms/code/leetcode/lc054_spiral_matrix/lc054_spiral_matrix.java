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
```rust
fn spiral_order(matrix: Vec<Vec<i32>>) -> Vec<i32> {
    let deltas = vec![[1, 0], [0, 1], [-1, 0], [0, -1]];
    let mut list = Vec::new();
    if matrix.is_empty() {
        return list;
    }

    let (mut top, mut bottom, mut left, mut right) = (0, matrix.len() as usize - 1, 0, matrix[0].len() as usize - 1);
    let mut x = 0;
    let mut y = 0;

    while top <= bottom && left <= right {
        if x > right {
            list.push(matrix[y][x]);
            for _ in 0..top {
                list.push(-matrix[y][x]);
            }
            for _ in 0..(right - x + 1) {
                list.push(-matrix[y][x - 1]);
            }
            for _ in 0..(bottom - y + 1) {
                list.push(-matrix[y - 1][x - 1]);
            }
            top += 1;
            bottom -= 1;
            left -= 1;
            y = top;
        } else if y > bottom {
            for _ in 0..left {
                list.push(-matrix[y][x]);
            }
            for _ in 0..(bottom - y + 1) {
                list.push(matrix[y][x]);
            }
            for _ in 0..(right - x + 1) {
                list.push(-matrix[y][x + 1]);
            }
            left += 1;
            right -= 1;
            x = right;
        } else if x < left {
            for _ in 0..left {
                list.push(matrix[y][x]);
            }
            for _ in 0..(top - y + 1) {
                list.push(-matrix[y + 1][x]);
            }
            for _ in 0..(right - x + 1) {
                list.push(matrix[y + 1][x + 1]);
            }
            bottom -= 1;
            right -= 1;
            y = bottom;
        } else if y < top {
            for _ in 0..bottom {
                list.push(-matrix[y][x]);
            }
            for _ in 0..(left - x + 1) {
                list.push(matrix[y][x - 1]);
            }
            for _ in 0..(top - y + 1) {
                list.push(matrix[y + 1][x]);
            }
            left += 1;
            top -= 1;
            x = left;
        } else {
            x += deltas[(y - top) % 4][0];
            y += deltas[(y - top) % 4][1];
        }
    }

    list
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_258_and_40_minus_1() {
        let matrix = vec![
            vec![2, 5, 8],
            vec![4, 0, -1],
        ];
        let expected = vec![2, 5, 8, -1, 0, 4];
        assert_eq!(spiral_order(matrix), expected);
    }

    #[test]
    fn test_25_and_840_minus_1() {
        let matrix = vec![
            vec![2, 5],
            vec![8, 4],
            vec![0, -1],
        ];
        let expected = vec![2, 5, 4, -1, 0, 8];
        assert_eq!(spiral_order(matrix), expected);
    }

    #[test]
    fn test_nothing() {
        let matrix = vec![];
        let result = spiral_order(matrix);
        assert_eq!(result.len(), 0);
    }
}
```