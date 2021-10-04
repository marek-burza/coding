package geeksforgeeks.gfg6310_find_the_nearest_locker_in_the_city;

import static geeksforgeeks.gfg6310_find_the_nearest_locker_in_the_city.GFG6310FindTheNearestLockerInTheCity.getLockerDistanceGrid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GFG6310FindTheNearestLockerInTheCityTests {
    @Test
    public void testExample1() throws Exception {
        int[][] result = getLockerDistanceGrid(3, 5, new int[] { 1 }, new int[] { 1 });
        int[][] expected = { { 0, 1, 2, 3, 4 }, { 1, 2, 3, 4, 5 }, { 2, 3, 4, 5, 6 } };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }

    @Test
    public void testExample2() throws Exception {
        int[][] result = getLockerDistanceGrid(5, 7, new int[] { 2, 4 }, new int[] { 3, 7 });
        int[][] expected = { { 3, 2, 1, 2, 3, 4, 3 }, { 2, 1, 0, 1, 2, 3, 2 }, { 3, 2, 1, 2, 3, 2, 1 },
                { 4, 3, 2, 3, 2, 1, 0 }, { 5, 4, 3, 4, 3, 2, 1 } };
        assertEquals(expected.length, result.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], result[row]);
        }
    }
}
