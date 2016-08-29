package hackerrank.camelcase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/camelcase
 */
public class TestSolution {
    @Test
    public void test_example() {
        assertEquals(5, Solution.countWords("saveChangesInTheEditor"));
    }
}
