package hackerrank.kaprekar_numbers;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/kaprekar-numbers
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test6() throws Exception {
        SolutionTester.test(Solution.class, "6");
    }

    @Test
    public void test06() throws Exception {
        SolutionTester.test(Solution.class, "06");
    }
}
