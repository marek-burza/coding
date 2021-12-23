package leetcode.lc260_single_number_iii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public final class LC260SingleNumberIIITests {
    @Test
    public void test() throws Exception {
        int[] result = new LC260SingleNumberIII().singleNumber(new int[] { 1, 2, 1, 3, 2, 5 });
        Arrays.sort(result);
        assertArrayEquals(new int[] { 3, 5 }, result);
    }
}
