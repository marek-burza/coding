package leetcode.lc191_number_of_1_bits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC191NumberOf1BitsTests {
    @Test
    public void test11() throws Exception {
        assertEquals(3, new LC191NumberOf1Bits().hammingWeight(11));
    }
}
