package leetcode.lc038_count_and_say;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC038CountAndSayTests {
    @Test
    public void test1() throws Exception {
        assertEquals("1", new LC038CountAndSay().countAndSay(1));
    }

    @Test
    public void test2() throws Exception {
        assertEquals("11", new LC038CountAndSay().countAndSay(2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals("21", new LC038CountAndSay().countAndSay(3));
    }

    @Test
    public void test4() throws Exception {
        assertEquals("1211", new LC038CountAndSay().countAndSay(4));
    }

    @Test
    public void test5() throws Exception {
        assertEquals("111221", new LC038CountAndSay().countAndSay(5));
    }

    @Test
    public void test0() throws Exception {
        assertNull(new LC038CountAndSay().countAndSay(0));
    }
}
