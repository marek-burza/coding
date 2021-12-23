package leetcode.lc299_bulls_and_cows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC299BullsAndCowsTests {
    @Test
    public void test1807And7810() throws Exception {
        assertEquals("1A3B", new LC299BullsAndCows().getHint("1807", "7810"));
    }

    @Test
    public void test1123And0111() throws Exception {
        assertEquals("1A1B", new LC299BullsAndCows().getHint("1123", "0111"));
    }

    @Test
    public void test1122And2211() throws Exception {
        assertEquals("0A4B", new LC299BullsAndCows().getHint("1122", "2211"));
    }

    @Test
    public void test11And10() throws Exception {
        assertEquals("1A0B", new LC299BullsAndCows().getHint("11", "10"));
    }
}
