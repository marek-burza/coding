package hackerrank.divisible_sum_pairs;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/divisible-sum-pairs
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test02() throws Exception {
        SolutionTester.test(Solution.class, "02");
    }

    @Test
    public void test06() throws Exception {
        SolutionTester.test(Solution.class, "06");
    }
}
