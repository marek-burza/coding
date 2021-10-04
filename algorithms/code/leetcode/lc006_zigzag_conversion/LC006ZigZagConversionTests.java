package leetcode.lc006_zigzag_conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC006ZigZagConversionTests {
    @Test
    public void test1() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
    }

    @Test
    public void test2() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void test3() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertEquals("A", solution.convert("A", 1));
    }

    @Test
    public void testAbcd() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertEquals("ABDC", solution.convert("ABCD", 3));
    }

    @Test
    public void testAbc() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertEquals("ACB", solution.convert("ABC", 2));
    }

    @Test
    public void testNothing() throws Exception {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertNull(solution.convert(null, 2));
    }

    @Test
    public void testZero() {
        LC006ZigZagConversion solution;
        solution = new LC006ZigZagConversion();
        assertNull(solution.convert("A", 0));
    }
}
