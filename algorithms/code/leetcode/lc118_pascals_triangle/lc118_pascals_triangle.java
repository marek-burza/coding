package leetcode.lc118_pascals_triangle;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/ #easy
 */
public final class LC118PascalsTriangle {
    public List<List<Integer>> generate(final int numRows) {
        if (numRows < 0) {
            return null;
        }
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            triangle.add(row);
            row.add(1);
            if (0 < i) {
                List<Integer> above = triangle.get(i - 1);
                for (int j = 0; j < i - 1; j++) {
                    row.add(above.get(j) + above.get(j + 1));
                }
                row.add(1);
            }
        }
        return triangle;
    }
}
package leetcode.lc118_pascals_triangle;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC118PascalsTriangleTests {
    @Test
    public void test5() throws Exception {
        int[][] expected = { { 1 }, { 1, 1 }, { 1, 2, 1 }, { 1, 3, 3, 1 }, { 1, 4, 6, 4, 1 } };
        List<List<Integer>> result = new LC118PascalsTriangle().generate(5);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC118PascalsTriangle().generate(-1));
    }
}
```rust
pub struct PascalsTriangle {
    triangle: Vec<Vec<i32>>,
}

impl PascalsTriangle {
    pub fn generate(&mut self, numRows: i32) -> Option<Vec<Vec<i32>>> {
        if numRows < 0 {
            return None;
        }

        let mut triangle = vec![vec![]; (numRows as usize).try_into().unwrap()];

        for i in 0..(numRows as usize) {
            let row = (i + 1).try_into().unwrap();
            triangle[i].push(1);

            if i > 0 {
                let above: &Vec<i32> = &triangle[(i - 1) as usize];
                for j in 0..(i - 1) {
                    triangle[i].push(above[j] + above[j + 1]);
                }
                triangle[i].push(1);
            }
        }

        Some(triangle)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test5() {
        let mut triangle = PascalsTriangle { triangle: vec![] };
        let result = triangle.generate(5).unwrap();
        assert_eq!(result.len(), 5);
        for i in 0..result.len() {
            assert_eq!(result[i].len(), i + 1);
            for j in 0..i + 1 {
                assert_eq!(result[i][j], if j == 0 || j == i { 1 } else { result[(i - 1) as usize][j - 1] + result[(i - 1) as usize][j] });
            }
        }
    }

    #[test]
    fn testNothing() {
        let mut triangle = PascalsTriangle { triangle: vec![] };
        assert!(triangle.generate(-1).is_none());
    }
}
```