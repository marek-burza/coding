package leetcode.lc273_integer_to_english_words;

/**
 * https://leetcode.com/problems/integer-to-english-words/ #hard
 */
public final class LC273IntegerToEnglishWords {
    private static final String[] MAGNITUDE = { "", " Thousand", " Million", " Billion", " Trillion", " Quadrillion",
            " Quintillion", " Sextillion", " Septillion", " Octillion", " Nonillion" };

    private static final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety" };

    private static final String[] DIGITS = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };

    private String tripleToWords(final int iValue) {
        int i = iValue;
        StringBuilder result = new StringBuilder();
        if (i >= 100) {
            result.append(DIGITS[i / 100]);
            result.append(" Hundred");
            i %= 100;
        }
        if (0 != i && result.length() != 0) {
            result.append(" ");
        }
        if (i <= 19) {
            result.append(DIGITS[i]);
        } else {
            result.append(TENS[i / 10]);
            i %= 10;
            if (0 != i) {
                result.append(" ");
                result.append(DIGITS[i]);
            }
        }
        return result.toString();
    }

    public String numberToWords(final int iValue) {
        int i = iValue;
        if (i == 0) {
            return "Zero";
        }
        StringBuilder result = new StringBuilder();
        int position = 0;
        while (0 != i) {
            String vocalization = tripleToWords(i % 1000);
            if (vocalization.length() != 0) {
                if (result.length() != 0) {
                    result.insert(0, " ");
                }
                result.insert(0, MAGNITUDE[position]);
                result.insert(0, vocalization);
            }
            i /= 1000;
            position++;
        }
        return result.toString();
    }
}
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
