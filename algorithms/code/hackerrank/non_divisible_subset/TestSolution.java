package hackerrank.non_divisible_subset;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/non-divisible-subset
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test06() throws Exception {
        SolutionTester.test(Solution.class, "06");
    }

    @Test
    public void test07() throws Exception {
        SolutionTester.test(Solution.class, "07");
    }

    @Test
    public void test16() throws Exception {
        SolutionTester.test(Solution.class, "16");
    }
}
