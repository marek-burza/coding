package leetcode.lc318_maximum_product_of_word_lengths;

/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * #medium
 */
public final class LC318MaximumProductOfWordLengths {
    public int maxProduct(final String[] words) {
        int[] signature = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char character : words[i].toCharArray()) {
                signature[i] |= 1 << (character - 'a');
            }
        }
        int maximum = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((signature[i] & signature[j]) == 0) {
                    maximum = Math.max(maximum, words[i].length() * words[j].length());
                }
            }
        }
        return maximum;
    }
}
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
