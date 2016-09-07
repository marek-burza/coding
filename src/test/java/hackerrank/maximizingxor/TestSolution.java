package hackerrank.maximizingxor;

import hackerrank.SolutionTester;
import org.junit.Test;

/**
 * https://www.hackerrank.com/challenges/maximizing-xor
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
    public void test_02() throws Exception {
        SolutionTester.test(Solution.class, "input02.txt", "output02.txt");
    }
}
