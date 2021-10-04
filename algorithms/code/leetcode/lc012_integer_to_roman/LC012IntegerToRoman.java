package leetcode.lc012_integer_to_roman;

/**
 * https://leetcode.com/problems/integer-to-roman/
 */
public final class LC012IntegerToRoman {
    private String[] digits = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    private int[] weights = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    public String intToRoman(final int num) {
        int number = num;
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            int multiple = number / weights[i];
            for (int j = 0; j < multiple; j++) {
                roman.append(digits[i]);
            }
            number -= multiple * weights[i];
        }
        return roman.toString();
    }
}
