package leetcode.lc136_single_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC136SingleNumberTests {
    @Test
    public void test1() throws Exception {
        assertEquals(1, new LC136SingleNumber().singleNumber(new int[] { 1 }));
    }

    @Test
    public void test1And2And1() throws Exception {
        assertEquals(2, new LC136SingleNumber().singleNumber(new int[] { 1, 2, 1 }));
    }
}
