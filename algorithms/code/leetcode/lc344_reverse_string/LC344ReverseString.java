package leetcode.lc344_reverse_string;

/**
 * https://leetcode.com/problems/reverse-string/ #easy
 */
public final class LC344ReverseString {
    public void reverseString(final char[] s) {
        for (int i = 0; i < Math.round(s.length / 2.0); i++) {
            char exchange = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = exchange;
        }
    }
}
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
