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
