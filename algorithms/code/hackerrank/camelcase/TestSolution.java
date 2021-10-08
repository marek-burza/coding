package hackerrank.camelcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/camelcase
 */
public class TestSolution {
    @Test
    public void testExample() {
        assertEquals(5, SolutionCore.countWords("saveChangesInTheEditor"));
    }
}
