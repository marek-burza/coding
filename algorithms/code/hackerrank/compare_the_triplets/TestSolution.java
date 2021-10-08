package hackerrank.compare_the_triplets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://www.hackerrank.com/challenges/compare-the-triplets
 */
public class TestSolution {
    @Test
    public void testExample0() {
        assertArrayEquals(new int[] { 1, 1 },
                SolutionCore.compareTriplets(new int[] { 5, 6, 7 }, new int[] { 3, 6, 10 }));
    }

    @Test
    public void testExample1() {
        assertArrayEquals(new int[] { 2, 1 },
                SolutionCore.compareTriplets(new int[] { 17, 28, 30 }, new int[] { 99, 16, 8 }));
    }
}
