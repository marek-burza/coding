package leetcode.lc007_reverse_integer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC007ReverseIntegerTests {
    @Test
    public void test2000000002() throws Exception {
        LC007ReverseInteger solution;
        solution = new LC007ReverseInteger();
        assertEquals(2000000002, solution.reverse(2000000002));
    }

    @Test
    public void testMinus2147483648() throws Exception {
        LC007ReverseInteger solution;
        solution = new LC007ReverseInteger();
        assertEquals(0, solution.reverse(-2147483648));
    }

    @Test
    public void test1000000003() throws Exception {
        LC007ReverseInteger solution;
        solution = new LC007ReverseInteger();
        assertEquals(0, solution.reverse(1000000003));
    }

    @Test
    public void test1534236469() throws Exception {
        LC007ReverseInteger solution;
        solution = new LC007ReverseInteger();
        assertEquals(0, solution.reverse(1534236469));
    }

    @Test
    public void testMinus321() throws Exception {
        LC007ReverseInteger solution;
        solution = new LC007ReverseInteger();
        assertEquals(-123, solution.reverse(-321));
    }
}
