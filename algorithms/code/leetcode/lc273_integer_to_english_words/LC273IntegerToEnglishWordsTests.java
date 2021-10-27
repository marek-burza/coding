package leetcode.lc273_integer_to_english_words;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC273IntegerToEnglishWordsTests {
    @Test
    public void test123() {
        assertEquals("One Hundred Twenty Three", new LC273IntegerToEnglishWords().numberToWords(123));
    }

    @Test
    public void test12345() {
        assertEquals("Twelve Thousand Three Hundred Forty Five", new LC273IntegerToEnglishWords().numberToWords(12345));
    }

    @Test
    public void test1234567() {
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
                new LC273IntegerToEnglishWords().numberToWords(1234567));
    }

    @Test
    public void test91() {
        assertEquals("Ninety One", new LC273IntegerToEnglishWords().numberToWords(91));
    }

    @Test
    public void test19() {
        assertEquals("Nineteen", new LC273IntegerToEnglishWords().numberToWords(19));
    }

    @Test
    public void test100() {
        assertEquals("One Hundred", new LC273IntegerToEnglishWords().numberToWords(100));
    }

    @Test
    public void test0() {
        assertEquals("Zero", new LC273IntegerToEnglishWords().numberToWords(0));
    }

    @Test
    public void test1000() {
        assertEquals("One Thousand", new LC273IntegerToEnglishWords().numberToWords(1000));
    }

    @Test
    public void test20() {
        assertEquals("Twenty", new LC273IntegerToEnglishWords().numberToWords(20));
    }
}
