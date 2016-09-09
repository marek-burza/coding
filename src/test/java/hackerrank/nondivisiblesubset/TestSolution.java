package hackerrank.nondivisiblesubset;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/non-divisible-subset
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_06() throws Exception {
        SolutionTester.test(Solution.class, "input06.txt", "output06.txt");
    }

    @Test
    public void test_07() throws Exception {
        SolutionTester.test(Solution.class, "input07.txt", "output07.txt");
    }
}
