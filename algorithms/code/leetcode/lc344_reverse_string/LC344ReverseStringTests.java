package leetcode.lc344_reverse_string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC344ReverseStringTests {
    @Test
    public void testExample() throws Exception {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        new LC344ReverseString().reverseString(s);
        assertArrayEquals(new char[] { 'o', 'l', 'l', 'e', 'h' }, s);
    }
}
