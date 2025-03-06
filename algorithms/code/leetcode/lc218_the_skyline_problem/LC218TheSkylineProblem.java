package leetcode.lc218_the_skyline_problem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-skyline-problem/ #hard
 */
public final class LC218TheSkylineProblem {
    private static class ByIndex implements Comparator<int[]>, Serializable {
        private final int index;

        ByIndex(final int indexValue) {
            index = indexValue;
        }

        @Override
        public int compare(final int[] array1, final int[] array2) {
            if (array1[index] < array2[index]) {
                return 1;
            }
            if (array1[index] > array2[index]) {
                return -1;
            }
            return 0;
        }
    }

    public List<int[]> getSkyline(final int[][] buildings) {
        List<int[]> skyline = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return skyline;
        }
        // Build list of spots
        NavigableMap<Integer, List<int[]>> spots = new TreeMap<>();
        for (int[] building : buildings) {
            if (building[0] == building[1]) {
                continue;
            }
            for (int i = 0; i < 2; i++) {
                List<int[]> list = spots.get(building[i]);
                if (list == null) {
                    list = new ArrayList<>();
                    spots.put(building[i], list);
                }
                list.add(building);
            }
        }
        // Prepare view
        int[] ground = { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 };
        PriorityQueue<int[]> view = new PriorityQueue<>(buildings.length, new ByIndex(2));
        view.add(ground);
        // Check all spots and build skyline
        int current = 0;
        for (int at : spots.keySet()) {
            for (int[] building : spots.get(at)) {
                if (at == building[0]) {
                    // Building entering the view
                    view.add(building);
                } else {
                    // Building leaving the view
                    view.remove(building);
                }
            }
            int next = view.peek()[2];
            if (current != next) {
                int[] point = { at, next };
                skyline.add(point);
            }
            current = next;
        }
        return skyline;
    }
}
package leetcode.lc218_the_skyline_problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class LC218TheSkylineProblemTests {
    private void generic(final int[][] buildings, final int[][] expected) throws Exception {
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        List<int[]> skyline = solution.getSkyline(buildings);
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
    }

    @Test
    public void testExample1() throws Exception {
        int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
        int[][] expected = { { 2, 10 }, { 3, 15 }, { 7, 12 }, { 12, 0 }, { 15, 10 }, { 20, 8 }, { 24, 0 } };
        generic(buildings, expected);
    }

    @Test
    public void testExample2() throws Exception {
        int[][] buildings = { { 0, 2, 3 }, { 2, 5, 3 } };
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        generic(buildings, expected);
    }

    @Test
    public void testCoverageGaps() throws Exception {
        int[][] buildings = { { 2, 5, 3 }, { 0, 2, 3 }, { 0, 0, 10 } };
        int[][] expected = { { 0, 3 }, { 5, 0 } };
        generic(buildings, expected);
        LC218TheSkylineProblem solution;
        solution = new LC218TheSkylineProblem();
        List<int[]> skyline;
        skyline = solution.getSkyline(new int[][] {});
        assertEquals(0, skyline.size());
        skyline = solution.getSkyline(null);
        assertEquals(0, skyline.size());
    }
}
