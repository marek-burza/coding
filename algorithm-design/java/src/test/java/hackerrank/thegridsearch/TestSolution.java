package hackerrank.thegridsearch;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/the-grid-search
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
    public void test_07() throws Exception {
        SolutionTester.test(Solution.class, "input07.txt", "output07.txt");
    }

    @Test
    public void test_08() throws Exception {
        SolutionTester.test(Solution.class, "input08.txt", "output08.txt");
    }

    @Test
    public void test_09() throws Exception {
        SolutionTester.test(Solution.class, "input09.txt", "output09.txt");
    }

    @Test
    public void test_15() throws Exception {
        SolutionTester.test(Solution.class, "input15.txt", "output15.txt");
    }
}
