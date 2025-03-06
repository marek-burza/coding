package leetcode.lc345_reverse_vowels_of_a_string;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/ #easy
 */
public final class LC345ReverseVowelsOfAString {
    private boolean isVowel(final char letter) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
        for (char vowel : vowels) {
            if (vowel == letter) {
                return true;
            }
        }
        return false;
    }

    public String reverseVowels(final String s) {
        char[] text = s.toCharArray();
        for (int a = 0, z = text.length - 1; a < z;) {
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
package leetcode.lc345_reverse_vowels_of_a_string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC345ReverseVowelsOfAStringTests {
    @Test
    public void testExample1() throws Exception {
        assertEquals("holle", new LC345ReverseVowelsOfAString().reverseVowels("hello"));
    }

    @Test
    public void testExample2() throws Exception {
        assertEquals("leetcode", new LC345ReverseVowelsOfAString().reverseVowels("leotcede"));
    }

    @Test
    public void testOe() throws Exception {
        assertEquals("EO", new LC345ReverseVowelsOfAString().reverseVowels("OE"));
    }

    @Test
    public void testZt() throws Exception {
        assertEquals("zt", new LC345ReverseVowelsOfAString().reverseVowels("zt"));
    }
}
