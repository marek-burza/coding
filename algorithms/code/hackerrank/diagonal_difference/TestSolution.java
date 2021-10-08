package hackerrank.diagonal_difference;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/diagonal-difference
 */
public class TestSolution {
    @Test
    public void testExample() {
        int[][] matrix = { { 11, 2, 4 }, { 4, 5, 6 }, { 10, 8, -12 } };
        assertEquals(15, SolutionCore.diagonalDifference(3, matrix));
    }
}
