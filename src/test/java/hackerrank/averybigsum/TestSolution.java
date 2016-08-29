package hackerrank.averybigsum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/a-very-big-sum
 */
public class TestSolution {
    @Test
    public void test_example() {
        assertEquals(5000000015L, Solution.bigSum(5, new int[]{1000000001, 1000000002, 1000000003, 1000000004, 1000000005}));
    }
}
