package hackerrank.timeconversion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/time-conversion
 */
public class TestSolution {
    @Test
    public void test_070545PM() {
        assertEquals("19:05:45", Solution.toMilitary("07:05:45PM"));
    }

    @Test
    public void test_120000PM() {
        assertEquals("12:00:00", Solution.toMilitary("12:00:00PM"));
    }

    @Test
    public void test_120000AM() {
        assertEquals("00:00:00", Solution.toMilitary("12:00:00AM"));
    }
}
