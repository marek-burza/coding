package leetcode.lc119_pascals_triangle_ii;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/ #easy
 */
public final class LC119PascalsTriangleII {
    public List<Integer> getRow(final int rowIndex) {
        int index = rowIndex;
        index++;
        if (index < 0) {
            return null;
        }
        List<Integer> previous = null;
        List<Integer> current = null;
        for (int i = 0; i < index; i++) {
            current = new ArrayList<>();
            current.add(1);
            if (0 < i) {
                for (int j = 0; j < i - 1; j++) {
                    current.add(previous.get(j) + previous.get(j + 1));
                }
                current.add(1);
            }
            previous = current;
        }
        return current;
    }
}
package leetcode.lc119_pascals_triangle_ii;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC119PascalsTriangleIITests {
    @Test
    public void test3() throws Exception {
        int[] expected = { 1, 3, 3, 1 };
        List<Integer> result = new LC119PascalsTriangleII().getRow(3);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).intValue());
        }
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC119PascalsTriangleII().getRow(-2));
    }
}
```rust
// LeetCode Problem 119: Pascal's Triangle II

pub struct LC119PascalsTriangleII {}

impl LC119PascalsTriangleII {
    /// Returns the i-th row of Pascal's triangle.
    pub fn get_row(&self, rowIndex: i32) -> Option<Vec<i64>> {
        let mut index = rowIndex;
        index += 1;

        if index < 0 {
            return None;
        }

        let mut previous = None;
        let mut current = Some(vec![1]);
        for i in 0..index {
            *current = vec![1];
            for j in 1..i {
                current
                    .as_mut()
                    .unwrap()
                    .push(previous.as_ref().unwrap()[j - 1] + previous.as_ref().unwrap()[j]);
            }
            if i > 0 {
                current.push(1);
            }

            let temp = previous;
            previous = current;
            current = temp;
        }

        Some(current)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_3() -> Result<(), Box<dyn std::error::Error>> {
        let expected = vec![1, 3, 3, 1];
        let result = LC119PascalsTriangleII().get_row(3)?;
        assert_eq!(expected.len(), result.len());
        for (i, element) in expected.into_iter().enumerate() {
            assert_eq!(element, result[i].into());
        }
        Ok(())
    }

    #[test]
    fn test_nothing() -> Result<(), Box<dyn std::error::Error>> {
        LC119PascalsTriangleII().get_row(-2)?;
        Ok(())
    }
}
```