package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * #medium
 */
public class LC318MaximumProductOfWordLengths {
    public class Solution {
        public int maxProduct(String[] words) {
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

    @Test
    public void test_example_1() throws Exception {
        String[] input = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, new Solution().maxProduct(input));
    }

    @Test
    public void test_example_2() throws Exception {
        String[] input = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        assertEquals(4, new Solution().maxProduct(input));
    }

    @Test
    public void test_example_3() throws Exception {
        String[] input = {"a", "aa", "aaa", "aaaa"};
        assertEquals(0, new Solution().maxProduct(input));
    }
}
