package leetcode.lc067_add_binary;

/**
 * https://leetcode.com/problems/add-binary/ #easy
 */
public final class LC067AddBinary {
    public String addBinary(final String a, final String b) {
        char[] ar = new StringBuilder(a).reverse().toString().toCharArray();
        char[] br = new StringBuilder(b).reverse().toString().toCharArray();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < ar.length || i < br.length; i++) {
            int sum = carry;
            sum += i < ar.length ? ar[i] - '0' : 0;
            sum += i < br.length ? br[i] - '0' : 0;
            carry = sum >> 1;
            result.append(((sum & 1) == 0) ? '0' : '1');
        }
        if (carry == 1) {
            result.append('1');
        }
        return result.reverse().toString();
    }
}
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
