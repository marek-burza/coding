package leetcode.lc013_roman_to_integer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC013RomanToIntegerTests {
    @Test
    public void testMCMLIV() throws Exception {
        LC013RomanToInteger solution;
        solution = new LC013RomanToInteger();
        assertEquals(1954, solution.romanToInt("MCMLIV"));
    }

    @Test
    public void testNothing() throws Exception {
        LC013RomanToInteger solution;
        solution = new LC013RomanToInteger();
        assertEquals(0, solution.romanToInt(null));
    }
}
