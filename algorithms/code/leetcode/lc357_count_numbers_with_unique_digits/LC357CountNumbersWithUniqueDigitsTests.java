package leetcode.lc357_count_numbers_with_unique_digits;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC357CountNumbersWithUniqueDigitsTests {
    @Test
    public void testExample() throws Exception {
        assertEquals(91, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(2));
    }

    @Test
    public void test0() throws Exception {
        assertEquals(1, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(0));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(10, new LC357CountNumbersWithUniqueDigits().countNumbersWithUniqueDigits(1));
    }
}
