package hackerrank.tutorial_intro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/tutorial-intro
 */
public class TestSolution {
    @Test
    public void testExample() {
        int[] array = { 1, 4, 5, 7, 9, 12 };
        assertEquals(1, SolutionCore.search(4, 6, array));
    }

    @Test
    public void testMissing() {
        int[] array = { 1, 4, 5, 7, 9, 12 };
        assertEquals(-1, SolutionCore.search(-4, 6, array));
    }
}
