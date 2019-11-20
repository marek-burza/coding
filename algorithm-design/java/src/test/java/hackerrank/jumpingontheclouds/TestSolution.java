package hackerrank.jumpingontheclouds;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/jumping-on-the-clouds
 */
public class TestSolution {
    @Test
    public void test_example_0() throws Exception {
        SolutionTester.test(Solution.class, "input_example_0.txt", "output_example_0.txt");
    }

    @Test
    public void test_example_1() throws Exception {
        SolutionTester.test(Solution.class, "input_example_1.txt", "output_example_1.txt");
    }
}
