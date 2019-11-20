package hackerrank.manasaandstones;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/manasa-and-stones
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_03() throws Exception {
        SolutionTester.test(Solution.class, "input03.txt", "output03.txt");
    }
}
