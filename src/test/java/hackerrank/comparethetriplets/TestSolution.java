package hackerrank.comparethetriplets;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://www.hackerrank.com/challenges/compare-the-triplets
 */
public class TestSolution {
    @Test
    public void test_example() {
        assertArrayEquals(new int[]{1, 1}, Solution.compareTriplets(5, 6, 7, 3, 6, 10));
    }
}
