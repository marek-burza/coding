package hackerrank.the_time_in_words;

import hackerrank.SolutionTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/the-time-in-words
 */
public class TestSolution {
    @Test
    public void testExample0() throws Exception {
        SolutionTester.test(Solution.class, "_example_0");
    }

    @Test
    public void testExample1() throws Exception {
        SolutionTester.test(Solution.class, "_example_1");
    }

    @Test
    public void testExample2() throws Exception {
        SolutionTester.test(Solution.class, "_example_2");
    }

    @Test
    public void test0359() throws Exception {
        assertEquals("one minute to four", SolutionCore.sayTime(3, 59));
    }

    @Test
    public void test0301() throws Exception {
        assertEquals("one minute past three", SolutionCore.sayTime(3, 1));
    }

    @Test
    public void test0345() throws Exception {
        assertEquals("quarter to four", SolutionCore.sayTime(3, 45));
    }

    @Test
    public void test0330() throws Exception {
        assertEquals("half past three", SolutionCore.sayTime(3, 30));
    }

    @Test
    public void test0320() throws Exception {
        assertEquals("twenty minutes past three", SolutionCore.sayTime(3, 20));
    }
}
