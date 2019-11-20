package hackerrank.icecreamparlor;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_02() throws Exception {
        SolutionTester.test(Solution.class, "input02.txt", "output02.txt");
    }
}
