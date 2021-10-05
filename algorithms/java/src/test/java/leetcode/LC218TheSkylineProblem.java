package leetcode;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 * #hard
 */
public class LC218TheSkylineProblem {
    public class Solution {
        private class ByIndex implements Comparator<int[]> {
            private final int index;
            private final boolean reverse;

            public ByIndex(int index, boolean reverse) {
                this.index = index;
                this.reverse = reverse;
            }

            @Override
            public int compare(int[] array1, int[] array2) {
                if (array1[index] < array2[index]) {
                    return reverse ? 1 : -1;
                }
                if (array1[index] > array2[index]) {
                    return reverse ? -1 : 1;
                }
                return 0;
            }
        }

        public List<int[]> getSkyline(int[][] buildings) {
            if (buildings == null || buildings.length == 0) {
                return new ArrayList<>();
            }
            // Build list of spots
            NavigableMap<Integer, Set<int[]>> spots = new TreeMap<>();
            for (int[] building : buildings) {
                if (building[0] == building[1]) {
                    continue;
                }
                for (int i = 0; i < 2; i++) {
                    Set<int[]> set = spots.get(building[i]);
                    if (set == null) {
                        set = new HashSet<>();
                        spots.put(building[i], set);
                    }
                    set.add(building);
                }
            }
            // Prepare view
            int[] ground = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
            PriorityQueue<int[]> view = new PriorityQueue<>(buildings.length, new ByIndex(2, true));
            view.add(ground);
            // Check all spots and build skyline
            List<int[]> skyline = new ArrayList<>();
            int current = 0;
            for (Map.Entry<Integer, Set<int[]>> spot : spots.entrySet()) {
                int at = spot.getKey();
                for (int[] building : spot.getValue()) {
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
                    int[] point = {at, next};
                    skyline.add(point);
                }
                current = next;
            }
            return skyline;
        }
    }

    @Test
    public void test_example() throws Exception {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<int[]> skyline = new Solution().getSkyline(buildings);
        int[][] expected = {{2, 10}, {3, 15}, {7, 12}, {12, 0}, {15, 10}, {20, 8}, {24, 0}};
        assertEquals(expected.length, skyline.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, skyline.get(i).length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], skyline.get(i)[j]);
            }
        }
    }
}
