package hackerrank.simplearraysum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/simple-array-sum
 */
public class TestSolution {
    @Test
    public void test_example() {
        assertEquals(31, Solution.simpleArraySum(6, new int[]{1, 2, 3, 4, 10, 11, 31}));
    }
}
