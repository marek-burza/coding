package leetcode.lc345_reverse_vowels_of_a_string;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * #easy
 */
public final class LC345ReverseVowelsOfAString {
    public final class Solution {
        private boolean isVowel(final char letter) {
            char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
            for (char vowel : vowels) {
                if (vowel == letter) {
                    return true;
                }
            }
            return false;
        }

        public String reverseVowels(final String s) {
            char[] text = s.toCharArray();
            for (int a = 0, z = text.length - 1; a < z; ) {
                while (a < text.length && !isVowel(text[a])) {
                    a++;
                }
                while (z >= 0 && !isVowel(text[z])) {
                    z--;
                }
                if (a < z) {
                    char exchange = text[a];
                    text[a] = text[z];
                    text[z] = exchange;
                    a++;
                    z--;
                }
            }
            return new String(text);
        }
    }

    @Test
    public void test_example_1() throws Exception {
        assertEquals("holle", new Solution().reverseVowels("hello"));
    }

    @Test
    public void test_example_2() throws Exception {
        assertEquals("leetcode", new Solution().reverseVowels("leotcede"));
    }

    @Test
    public void test_OE() throws Exception {
        assertEquals("EO", new Solution().reverseVowels("OE"));
    }

    @Test
    public void test_zt() throws Exception {
        assertEquals("zt", new Solution().reverseVowels("zt"));
    }
}
