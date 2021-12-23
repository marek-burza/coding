package leetcode.lc343_integer_break;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC343IntegerBreakTests {
    @Test
    public void test2() throws Exception {
        assertEquals(1, new LC343IntegerBreak().integerBreak(2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals(2, new LC343IntegerBreak().integerBreak(3));
    }

    @Test
    public void test4() throws Exception {
        assertEquals(4, new LC343IntegerBreak().integerBreak(4));
    }

    @Test
    public void test5() throws Exception {
        assertEquals(6, new LC343IntegerBreak().integerBreak(5));
    }

    @Test
    public void test6() throws Exception {
        assertEquals(9, new LC343IntegerBreak().integerBreak(6));
    }

    @Test
    public void test7() throws Exception {
        assertEquals(12, new LC343IntegerBreak().integerBreak(7));
    }

    @Test
    public void test8() throws Exception {
        assertEquals(18, new LC343IntegerBreak().integerBreak(8));
    }

    @Test
    public void test9() throws Exception {
        assertEquals(27, new LC343IntegerBreak().integerBreak(9));
    }

    @Test
    public void test10() throws Exception {
        assertEquals(36, new LC343IntegerBreak().integerBreak(10));
    }
}
