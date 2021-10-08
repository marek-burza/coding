package hackerrank.connected_cell_in_a_grid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static class Pair<X, Y> {
        public final X x;
        public final Y y;

        Pair(final X xValue, final Y yValue) {
            x = xValue;
            y = yValue;
        }
    }

    private static class Swath {
        public final HashSet<Swath> adjacent = new HashSet<>();
    }

    protected static final class IslandHunter {
        private final List<Swath> land = new LinkedList<>();
        private final HashMap<Long, Swath> mapping = new HashMap<>();

        public IslandHunter(final List<Pair<Integer, Integer>> landValue) {
            landValue.forEach(this::markAsLand);
        }

        private long key(final int x, final int y) {
            return (((long) x) << 32) | (long) y;
        }

        private int[][] deltas = { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 },
                { 1, 1 } };

        private void markAsLand(final Pair<Integer, Integer> location) {
            long key = key(location.x, location.y);
            // if (!mapping.containsKey(key)) {
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
            // }
        }

        public int measureBiggestIsland() {
            Set<Swath> seen = new HashSet<>();
            int maximum = 0;
            for (Swath swath : land) {
                maximum = Math.max(maximum, traverse(swath, seen));
            }
            return maximum;
        }

        private int traverse(final Swath swath, final Set<Swath> seen) {
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
