package hackerrank.tutorialintro;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/tutorial-intro
 */
public class TestSolution {
    @Test
    public void test_example() {
        int[] array = {1, 4, 5, 7, 9, 12};
        assertEquals(1, Solution.search(4, 6, array));
    }
}
