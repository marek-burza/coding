package hackerrank.primsmstsub;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public class TestSolution {
    @Test
    public void test_example() throws Exception {
        SolutionTester.test(Solution.class, "input_example.txt", "output_example.txt");
    }

    @Test
    public void test_05() throws Exception {
        SolutionTester.test(Solution.class, "input05.txt", "output05.txt");
    }

    @Test
    public void test_06() throws Exception {
        SolutionTester.test(Solution.class, "input06.txt", "output06.txt");
    }
}
