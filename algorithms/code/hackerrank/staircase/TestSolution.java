package hackerrank.staircase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/staircase
 */
public class TestSolution {
    @Test
    public void testExample() {
        String[] expected = { "     #", "    ##", "   ###", "  ####", " #####", "######" };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], SolutionCore.generateStep(expected.length, i));
        }
    }
}
