package hackerrank.a_very_big_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/a-very-big-sum
 */
public class TestSolution {
    @Test
    public void testExample() {
        assertEquals(5000000015L, SolutionCore.bigSum(5, new int[]{1000000001, 1000000002, 1000000003, 1000000004, 1000000005}));
    }
}
