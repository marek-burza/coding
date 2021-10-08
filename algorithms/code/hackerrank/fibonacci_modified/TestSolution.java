package hackerrank.fibonacci_modified;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public class TestSolution {
    @Test
    public void testExample1() {
        assertEquals("5", SolutionCore.fibonacciModified(0, 1, 5));
    }

    @Test
    public void testExample2() {
        assertEquals("84266613096281243382112", SolutionCore.fibonacciModified(0, 1, 10));
    }
}
