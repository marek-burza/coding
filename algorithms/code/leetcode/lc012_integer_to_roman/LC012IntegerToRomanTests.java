package leetcode.lc012_integer_to_roman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC012IntegerToRomanTests {
    @Test
    public void test1234() throws Exception {
        LC012IntegerToRoman solution;
        solution = new LC012IntegerToRoman();
        assertEquals("MCCXXXIV", solution.intToRoman(1234));
    }

    @Test
    public void test9() throws Exception {
        LC012IntegerToRoman solution;
        solution = new LC012IntegerToRoman();
        assertEquals("IX", solution.intToRoman(9));
    }
}
