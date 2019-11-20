package hackerrank.diagonaldifference;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference
 */
public class TestSolution {
    @Test
    public void test_example() {
        int[][] matrix = {
                {11, 2, 4},
                {4, 5, 6},
                {10, 8, -12}
        };
        assertEquals(15, Solution.diagonalDifference(3, matrix));
    }
}
