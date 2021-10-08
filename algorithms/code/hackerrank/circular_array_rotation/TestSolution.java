package hackerrank.circular_array_rotation;

import org.junit.jupiter.api.Test;

import hackerrank.SolutionTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/circular-array-rotation
 */
public class TestSolution {
    @Test
    public void testExample() {
        int[] array = { 1, 2, 3 };
        int[] expected = { 2, 3, 1 };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], SolutionCore.query(array.length, 2, array, i));
        }
    }

    @Test
    public void testBigger() throws Exception {
        SolutionTester.test(Solution.class, "_bigger");
    }
}
