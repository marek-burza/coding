package hackerrank.missing_numbers;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/missing-numbers
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test01() throws Exception {
        SolutionTester.test(Solution.class, "01");
    }

    @Test
    public void test03() throws Exception {
        SolutionTester.test(Solution.class, "03");
    }
}
