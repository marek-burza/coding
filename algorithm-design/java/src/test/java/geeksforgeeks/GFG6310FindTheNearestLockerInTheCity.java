package geeksforgeeks;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * http://qa.geeksforgeeks.org/6310
 */
public class GFG6310FindTheNearestLockerInTheCity {
    private static class Solution {
//        static int[][] getLockerDistanceGridBruteForce(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) {
//            int n = Math.min(lockerXCoordinates.length, lockerYCoordinates.length);
//            int[][] result = new int[cityLength][cityWidth];
//            for (int x = 0; x < cityLength; x++) {
//                for (int y = 0; y < cityWidth; y++) {
//                    result[x][y] = Integer.MAX_VALUE;
//                    for (int i = 0; i < n; i++) {
//                        int d = Math.abs(x - lockerXCoordinates[i] + 1) + Math.abs(y - lockerYCoordinates[i] + 1);
//                        result[x][y] = Math.min(result[x][y], d);
//                    }
//                }
//            }
//            return result;
//        }

        private static class Spot {
            final int x;
            final int y;
            final int distance;

            Spot(int x, int y, int distance) {
                this.x = x;
                this.y = y;
                this.distance = distance;
            }
        }

        static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates,
                                             int[] lockerYCoordinates) {
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
            int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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
                        if (dx < 0 || result.length <= dx) continue;
                        int dy = y + delta[1];
                        if (dy < 0 || result[0].length <= dy) continue;
                        // Calculate new distance and check if less than current shortest one
                        int dd = d + 1;
                        if (result[dx][dy] <= dd) continue;
                        // Valid so update shortest distance and traverse there
                        result[dx][dy] = dd;
                        queue.add(new Spot(dx, dy, dd));
                    }
                }
            }
            return result;
        }
    }

    @Test
    public void test_example_1() throws Exception {
        int[][] result = Solution.getLockerDistanceGrid(3, 5, new int[]{1}, new int[]{1});
        int[][] expected = {
                {0, 1, 2, 3, 4},
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6}
        };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }

    @Test
    public void test_example_2() throws Exception {
        int[][] result = Solution.getLockerDistanceGrid(5, 7, new int[]{2, 4}, new int[]{3, 7});
        int[][] expected = {
                {3, 2, 1, 2, 3, 4, 3},
                {2, 1, 0, 1, 2, 3, 2},
                {3, 2, 1, 2, 3, 2, 1},
                {4, 3, 2, 3, 2, 1, 0},
                {5, 4, 3, 4, 3, 2, 1}
        };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }
}
