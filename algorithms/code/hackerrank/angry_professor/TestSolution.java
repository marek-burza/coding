package hackerrank.angry_professor;

import hackerrank.SolutionTester;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/angry-professor
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
}
