package leetcode.lc120_triangle;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC120TriangleTests {
    private List<List<Integer>> construct(final int[][] compact) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int[] array : compact) {
            List<Integer> line = new ArrayList<>();
            triangle.add(line);
            for (int value : array) {
                line.add(value);
            }
        }
        return triangle;
    }

    @Test
    public void testExample() throws Exception {
        int[][] triangle = {
                { 2 },
                { 3, 4 },
                { 6, 5, 7 },
                { 4, 1, 8, 3 }
        };
        assertEquals(11, new LC120Triangle().minimumTotal(construct(triangle)));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(0, new LC120Triangle().minimumTotal(null));
        assertEquals(0, new LC120Triangle().minimumTotal(new ArrayList<>()));
        List<List<Integer>> nothing = new ArrayList<>();
        nothing.add(new ArrayList<>());
        assertEquals(0, new LC120Triangle().minimumTotal(nothing));
    }
}
