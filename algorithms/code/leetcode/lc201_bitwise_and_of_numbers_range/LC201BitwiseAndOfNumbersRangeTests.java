package leetcode.lc201_bitwise_and_of_numbers_range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC201BitwiseAndOfNumbersRangeTests {
    @Test
    public void test5And7() throws Exception {
        assertEquals(4, new LC201BitwiseAndOfNumbersRange().rangeBitwiseAnd(5, 7));
    }
}
