package hackerrank.count_luck;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * https://www.hackerrank.com/challenges/count-luck
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void testEmpty() throws Exception {
        assertFalse(SolutionCore.check(new char[0][], 0));
    }
}
