package hackerrank.kaprekarnumbers;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/kaprekar-numbers
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_6() throws Exception {
        SolutionTester.test(Solution.class, "input6.txt", "output6.txt");
    }

    @Test
    public void test_06() throws Exception {
        SolutionTester.test(Solution.class, "input06.txt", "output06.txt");
    }
}
