package geeksforgeeks.gfg6310_find_the_nearest_locker_in_the_city;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * http://qa.geeksforgeeks.org/6310
 */
public final class GFG6310FindTheNearestLockerInTheCity {
    private GFG6310FindTheNearestLockerInTheCity() {
    }

    private static class Spot {
        public final int x;
        public final int y;
        public final int distance;

        Spot(final int xValue, final int yValue, final int distanceValue) {
            x = xValue;
            y = yValue;
            distance = distanceValue;
        }
    }

    public static int[][] getLockerDistanceGrid(final int cityLength, final int cityWidth,
            final int[] lockerXCoordinates, final int[] lockerYCoordinates) {
        // Fill all cells with max value distance
        int[][] result = new int[cityLength][cityWidth];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Fill locker locations with 0 distance
        int n = lockerXCoordinates.length;
        for (int i = 0; i < n; i++) {
            int x = lockerXCoordinates[i] - 1;
            int y = lockerYCoordinates[i] - 1;
            result[x][y] = 0;
        }
        // BFS queue
        Queue<Spot> queue = new LinkedList<>();
        // Go down, up, right, left
        int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int i = 0; i < n; i++) {
            // Add locker location to queue
            int x = lockerXCoordinates[i] - 1;
            int y = lockerYCoordinates[i] - 1;
            queue.add(new Spot(x, y, 0));
            // While we have unvisited cells
            while (queue.size() != 0) {
                Spot spot = queue.poll();
                int d = spot.distance;
                x = spot.x;
                y = spot.y;
                // For each direction from here
                for (int[] delta : deltas) {
                    // Calc new position and check if in the city
                    int dx = x + delta[0];
                    if (dx < 0 || result.length <= dx) {
                        continue;
                    }
                    int dy = y + delta[1];
                    if (dy < 0 || result[0].length <= dy) {
                        continue;
                    }
                    // Calculate new distance and check if less than current shortest one
                    int dd = d + 1;
                    if (result[dx][dy] <= dd) {
                        continue;
                    }
                    // Valid so update shortest distance and traverse there
                    result[dx][dy] = dd;
                    queue.add(new Spot(dx, dy, dd));
                }
            }
        }
        return result;
    }
}
