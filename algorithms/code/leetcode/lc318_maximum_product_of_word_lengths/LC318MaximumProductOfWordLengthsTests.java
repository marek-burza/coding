package leetcode.lc318_maximum_product_of_word_lengths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC318MaximumProductOfWordLengthsTests {
    @Test
    public void testExample1() throws Exception {
        String[] input = { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" };
        assertEquals(16, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }

    @Test
    public void testExample2() throws Exception {
        String[] input = { "a", "ab", "abc", "d", "cd", "bcd", "abcd" };
        assertEquals(4, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }

    @Test
    public void testExample3() throws Exception {
        String[] input = { "a", "aa", "aaa", "aaaa" };
        assertEquals(0, new LC318MaximumProductOfWordLengths().maxProduct(input));
    }
}
