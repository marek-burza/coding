package hackerrank.jumping_on_the_clouds;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/jumping-on-the-clouds
 */
public class TestSolution {
    @Test
    public void testExample0() throws Exception {
        SolutionTester.test(Solution.class, "_example_0");
    }

    @Test
    public void testExample1() throws Exception {
        SolutionTester.test(Solution.class, "_example_1");
    }

    @Test
    public void testMissingExample() throws Exception {
        assertEquals(1, SolutionCore.countJumps(new int[] { 0, 0 }));
    }
}
