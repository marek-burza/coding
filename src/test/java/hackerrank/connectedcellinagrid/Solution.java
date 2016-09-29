package hackerrank.connectedcellinagrid;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 */
public class Solution {
    private static InputStream inOverride = null;
    private static PrintStream outOverride = null;

    public static void main(String[] args) {
        if (null == inOverride) {
            inOverride = System.in;
        }
        if (null == outOverride) {
            outOverride = System.out;
        }
        Scanner in = new Scanner(inOverride);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] almost = new int[n];
        List<Pair<Integer, Integer>> land = new LinkedList<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (1 == in.nextInt()) {
                    land.add(new Pair<>(col, row));
                }
            }
        }
        outOverride.println(new IslandHunter(land).measureBiggestIsland());
    }

    private static class Pair<X, Y> {
        public final X x;
        public final Y y;

        private Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Swath {
        public final HashSet<Swath> adjacent = new HashSet<>();
    }

    private static class IslandHunter {
        private final List<Swath> land = new LinkedList<>();
        private final HashMap<Long, Swath> mapping = new HashMap<>();

        public IslandHunter(List<Pair<Integer, Integer>> land) {
            for (Pair<Integer, Integer> location : land) {
                markAsLand(location);
            }
        }

        private long key(int x, int y) {
            return (((long) x) << 32) | (long) y;
        }

        private int[][] deltas = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

        private void markAsLand(Pair<Integer, Integer> location) {
            long key = key(location.x, location.y);
            if (!mapping.containsKey(key)) {
                Swath swath = new Swath();
                land.add(swath);
                mapping.put(key, swath);
                for (int[] delta : deltas) {
                    int dx = location.x + delta[0];
                    int dy = location.y + delta[1];
                    key = key(dx, dy);
                    Swath near = mapping.get(key);
                    if (null != near) {
                        swath.adjacent.add(near);
                        near.adjacent.add(swath);
                    }
                }
            }
        }

        @SuppressWarnings("unused")
        public int getIslandCount() {
            Set<Swath> seen = new HashSet<>();
            int count = 0;
            for (Swath swath : land) {
                count += traverse(swath, seen) > 0 ? 1 : 0;
            }
            return count;
        }

        public int measureBiggestIsland() {
            Set<Swath> seen = new HashSet<>();
            int maximum = 0;
            for (Swath swath : land) {
                maximum = Math.max(maximum, traverse(swath, seen));
            }
            return maximum;
        }

        private int traverse(Swath swath, Set<Swath> seen) {
            if (seen.contains(swath)) {
                return 0;
            }
            seen.add(swath);
            int count = 1;
            for (Swath near : swath.adjacent) {
                count += traverse(near, seen);
            }
            return count;
        }
    }
}
