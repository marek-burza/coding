package hackerrank.fibonaccimodified;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public class TestSolution {
    @Test
    public void test_example_1() {
        assertEquals("5", Solution.fibonacciModified(0, 1, 5));
    }

    @Test
    public void test_example_2() {
        assertEquals("84266613096281243382112", Solution.fibonacciModified(0, 1, 10));
    }
}
