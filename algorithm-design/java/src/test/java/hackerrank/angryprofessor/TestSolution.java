package hackerrank.angryprofessor;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/angry-professor
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_01() throws Exception {
        SolutionTester.test(Solution.class, "input01.txt", "output01.txt");
    }
}
