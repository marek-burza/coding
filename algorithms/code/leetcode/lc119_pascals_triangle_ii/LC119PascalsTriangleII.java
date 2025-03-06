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
