package hackerrank.staircase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/staircase
 */
public class TestSolution {
    @Test
    public void test_example() {
        String[] expected = {
                "     #",
                "    ##",
                "   ###",
                "  ####",
                " #####",
                "######"
        };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], Solution.generateStep(expected.length, i));
        }
    }
}
