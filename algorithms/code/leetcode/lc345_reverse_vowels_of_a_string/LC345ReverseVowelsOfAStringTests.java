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
