package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/pascals-triangle/
 * #easy
 */
public class LC118PascalsTriangle {
    public class Solution {
        public List<List<Integer>> generate(int numRows) {
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

    @Test
    public void test_5() throws Exception {
        int[][] expected = {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}};
        List<List<Integer>> result = new Solution().generate(5);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, result.get(i).size());
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], result.get(i).get(j).intValue());
            }
        }
    }
}
