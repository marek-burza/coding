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
