package hackerrank.time_conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/time-conversion
 */
public class TestSolution {
    @Test
    public void test070545PM() {
        assertEquals("19:05:45", SolutionCore.toMilitary("07:05:45PM"));
    }

    @Test
    public void test120000PM() {
        assertEquals("12:00:00", SolutionCore.toMilitary("12:00:00PM"));
    }

    @Test
    public void test120000AM() {
        assertEquals("00:00:00", SolutionCore.toMilitary("12:00:00AM"));
    }
}
