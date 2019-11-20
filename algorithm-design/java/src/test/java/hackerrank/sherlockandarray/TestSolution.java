package hackerrank.sherlockandarray;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_00() throws Exception {
        SolutionTester.test(Solution.class, "input00.txt", "output00.txt");
    }
}
