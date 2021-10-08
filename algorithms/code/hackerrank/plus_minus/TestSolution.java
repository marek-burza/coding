package hackerrank.plus_minus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/plus-minus
 */
public class TestSolution {
    @Test
    public void testExample() {
        double[] fractions = SolutionCore.fractions(6, new int[] { -4, 3, -9, 0, 4, 1 });
        assertEquals(1.0 / 2.0, fractions[0], 0);
        assertEquals(1.0 / 3.0, fractions[1], 0);
        assertEquals(1.0 / 6.0, fractions[2], 0);
    }
}
