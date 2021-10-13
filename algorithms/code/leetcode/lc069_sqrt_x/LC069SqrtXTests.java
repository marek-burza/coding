package leetcode.lc069_sqrt_x;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC069SqrtXTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(4));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals(2, new LC069SqrtX().mySqrt(8));
    }

    @Test
    public void test64() throws Exception {
        assertEquals(8, new LC069SqrtX().mySqrt(64));
    }

    @Test
    public void test2() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(2));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(1, new LC069SqrtX().mySqrt(1));
    }
}
