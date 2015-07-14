package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pascals-triangle-ii/
 */
public class LC119PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        if (rowIndex < 0) {
            return null;
        }
        List<Integer> previous = null;
        List<Integer> current = null;
        for (int i = 0; i < rowIndex; i++) {
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

    public static void main(String[] arguments) {
        LC119PascalsTriangleII solution = new LC119PascalsTriangleII();
        System.out.println(solution.getRow(3));
    }
}
