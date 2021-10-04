package leetcode.lc008_string_to_integer_a_to_i;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC008StringToIntegerAToITests {
    @Test
    public void testMinusMinus3241() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(0, solution.myAtoi("--3241"));
    }

    @Test
    public void testPlusPlus3241() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(0, solution.myAtoi("++3241"));
    }

    @Test
    public void testMinusPlus3241() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(0, solution.myAtoi("-+3241"));
    }

    @Test
    public void testMinus3241() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(-3241, solution.myAtoi("-3241"));
    }

    @Test
    public void testSpaceMinusPlus3241A() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(-3241, solution.myAtoi(" -3241a"));
    }

    @Test
    public void test9223372036854775809() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(Integer.MAX_VALUE, solution.myAtoi("9223372036854775809"));
    }

    @Test
    public void testMinus9223372036854775809() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(Integer.MIN_VALUE, solution.myAtoi("-9223372036854775809"));
    }

    @Test
    public void testNothing() throws Exception {
        LC008StringToIntegerAToI solution;
        solution = new LC008StringToIntegerAToI();
        assertEquals(0, solution.myAtoi("nothing"));
    }
}
