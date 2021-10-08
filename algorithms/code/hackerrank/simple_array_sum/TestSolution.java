package hackerrank.simple_array_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/simple-array-sum
 */
public class TestSolution {
    @Test
    public void testExample() {
        assertEquals(31, SolutionCore.simpleArraySum(6, new int[] { 1, 2, 3, 4, 10, 11, 31 }));
    }
}
