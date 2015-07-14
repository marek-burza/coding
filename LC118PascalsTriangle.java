package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle/
 */
public class LC118PascalsTriangle {
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

    public static void main(String[] arguments) {
        LC118PascalsTriangle solution = new LC118PascalsTriangle();
        System.out.println(solution.generate(5));
    }
}
