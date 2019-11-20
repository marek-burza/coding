package hackerrank.plusminus;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/plus-minus
 */
public class TestSolution {
    @Test
    public void test_example() {
        double[] fractions = Solution.fractions(6, new int[]{-4, 3, -9, 0, 4, 1});
        assertEquals(1.0 / 2.0, fractions[0], 0);
        assertEquals(1.0 / 3.0, fractions[1], 0);
        assertEquals(1.0 / 6.0, fractions[2], 0);
    }
}
