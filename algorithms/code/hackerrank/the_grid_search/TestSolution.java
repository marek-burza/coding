package hackerrank.the_grid_search;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/the-grid-search
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test05() throws Exception {
        SolutionTester.test(Solution.class, "05");
    }

    @Test
    public void test07() throws Exception {
        SolutionTester.test(Solution.class, "07");
    }

    @Test
    public void test08() throws Exception {
        SolutionTester.test(Solution.class, "08");
    }

    @Test
    public void test09() throws Exception {
        SolutionTester.test(Solution.class, "09");
    }

    @Test
    public void test15() throws Exception {
        SolutionTester.test(Solution.class, "15");
    }
}
