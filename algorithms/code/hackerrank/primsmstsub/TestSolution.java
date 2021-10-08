package hackerrank.primsmstsub;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public class TestSolution {
    @Test
    public void testExample() throws Exception {
        SolutionTester.test(Solution.class, "_example");
    }

    @Test
    public void test05() throws Exception {
        SolutionTester.test(Solution.class, "05");
    }

    @Test
    public void test06() throws Exception {
        SolutionTester.test(Solution.class, "06");
    }
}
