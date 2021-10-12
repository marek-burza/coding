package leetcode;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/integer-to-english-words/
 * #hard
 */
public final class LC273IntegerToEnglishWords {
    public final class Solution {
        private final String[] MAGNITUDE = {
                "", " Thousand", " Million", " Billion", " Trillion", " Quadrillion", " Quintillion", " Sextillion",
                " Septillion", " Octillion", " Nonillion"
        };

        private final String[] TENS = {
                "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        private final String[] DIGITS = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };

        private String tripleToWords(final int i) {
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

        public String numberToWords(final int i) {
            if (i == 0) return "Zero";
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

    @Test
    public void test_123() {
        assertEquals("One Hundred Twenty Three", new Solution().numberToWords(123));
    }

    @Test
    public void test_12345() {
        assertEquals("Twelve Thousand Three Hundred Forty Five", new Solution().numberToWords(12345));
    }

    @Test
    public void test_1234567() {
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", new Solution().numberToWords(1234567));
    }

    @Test
    public void test_91() {
        assertEquals("Ninety One", new Solution().numberToWords(91));
    }

    @Test
    public void test_19() {
        assertEquals("Nineteen", new Solution().numberToWords(19));
    }

    @Test
    public void test_100() {
        assertEquals("One Hundred", new Solution().numberToWords(100));
    }
}
