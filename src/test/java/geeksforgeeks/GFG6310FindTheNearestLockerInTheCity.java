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
public class GFG6310FindTheNearestLockerInTheCity { // x y in result were flipped
    static int[][] getLockerDistanceGrid(int cityLength, int cityWidth, int[] lockerXCoordinates, int[] lockerYCoordinates) {
        // Fill all cells with max value distance
        int[][] result = new int[cityWidth][cityLength];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        // Fill locker locations with 0 distance
        int n = lockerXCoordinates.length;
        for (int i = 0; i < n; i++) {
            int x = lockerXCoordinates[i] - 1;
            int y = lockerYCoordinates[i] - 1;
            result[y][x] = 0;
        }
        // BFS queue
        Queue<Integer> ds = new LinkedList<>();
        Queue<Integer> xs = new LinkedList<>();
        Queue<Integer> ys = new LinkedList<>();
        // Go down, up, right, left
        int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < n; i++) {
            // Add locker location to queue
            int x = lockerXCoordinates[i] - 1;
            int y = lockerYCoordinates[i] - 1;
            ds.add(0);
            xs.add(x);
            ys.add(y);
            // While we have unvisited cells
            while (ds.size() != 0) {
                int d = ds.poll();
                x = xs.poll();
                y = ys.poll();
                // For each direction from here
                for (int j = 0; j < delta.length; j++) {
                    // Calc new position and check if in the city
                    int dx = x + delta[j][0];
                    if (dx < 0 || result[0].length <= dx) continue;
                    int dy = y + delta[j][1];
                    if (dy < 0 || result.length <= dy) continue;
                    // Calculate new distance and check if less than current shortest one
                    int dd = d + 1;
                    if (result[dy][dx] <= dd) continue;
                    // Valid so update shortest distance and traverse there
                    result[dy][dx] = dd;
                    ds.add(dd);
                    xs.add(dx);
                    ys.add(dy);
                }
            }
        }
        return result;
    }

    @Test
    public void test_example_1() throws Exception {
        int[][] result = GFG6310FindTheNearestLockerInTheCity.getLockerDistanceGrid(3, 5, new int[]{1}, new int[]{1});
        int[][] expected = {
                {0, 1, 2},
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5},
                {4, 5, 6}
        };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }

    @Test
    public void test_example_2() throws Exception {
        int[][] result = GFG6310FindTheNearestLockerInTheCity.getLockerDistanceGrid(5, 7, new int[]{2, 4}, new int[]{3, 7});
        int[][] expected = {
                {3, 2, 3, 4, 5},
                {2, 1, 2, 3, 4},
                {1, 0, 1, 2, 3},
                {2, 1, 2, 3, 4},
                {3, 2, 3, 2, 3},
                {4, 3, 2, 1, 2},
                {3, 2, 1, 0, 1}
        };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }
}
