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
