package leetcode.lc067_add_binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC067AddBinaryTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals("100", new LC067AddBinary().addBinary("11", "1"));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals("10101", new LC067AddBinary().addBinary("1010", "1011"));
    }

    @Test
    public void testExample1Reversed() throws Exception {
        assertEquals("100", new LC067AddBinary().addBinary("1", "11"));
    }

    @Test
    public void testNoCarry() throws Exception {
        assertEquals("1", new LC067AddBinary().addBinary("1", "0"));
    }
}
