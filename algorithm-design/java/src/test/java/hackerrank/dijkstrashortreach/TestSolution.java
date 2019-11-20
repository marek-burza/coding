package hackerrank.dijkstrashortreach;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/dijkstrashortreach
 */
public class TestSolution {
    @Test
    public void test_00() throws Exception {
        SolutionTester.test(Solution.class, "input00.txt", "output00.txt");
    }

    @Test
    public void test_01() throws Exception {
        SolutionTester.test(Solution.class, "input01.txt", "output01.txt");
    }

    @Test
    public void test_03() throws Exception {
        SolutionTester.test(Solution.class, "input03.txt", "output03.txt");
    }

    @Test
    public void test_04() throws Exception {
        SolutionTester.test(Solution.class, "input04.txt", "output04.txt");
    }

    @Test
    public void test_07() throws Exception {
        SolutionTester.test(Solution.class, "input07.txt", "output07.txt");
    }
}
