package hackerrank.icecream_parlor;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test02() throws Exception {
        SolutionTester.test(Solution.class, "02");
    }

    @Test
    public void testSame() throws Exception {
        int[] expected = new int[] { 3, 4 };
        assertArrayEquals(expected, SolutionCore.which(6, new int[] { 3, 1, 2, 4 }));
    }

    @Test
    public void testNone() throws Exception {
        int[] expected = new int[] { 0, 0 };
        assertArrayEquals(expected, SolutionCore.which(10, new int[] { 3, 1, 2, 4 }));
    }
}
