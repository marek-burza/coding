package hackerrank.encryption;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/encryption
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

    @Test
    public void test_example_2() throws Exception {
        SolutionTester.test(Solution.class, "input_example_2.txt", "output_example_2.txt");
    }

    @Test
    public void test_example_3() throws Exception {
        SolutionTester.test(Solution.class, "input_example_3.txt", "output_example_3.txt");
    }
}
