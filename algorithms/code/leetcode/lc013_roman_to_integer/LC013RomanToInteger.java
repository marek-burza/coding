package leetcode.lc013_roman_to_integer;

import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 * #easy
 */
public final class LC013RomanToInteger {
    private static final Map<Character, Integer> LUT = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M',
            1000);

    public int romanToInt(final String s) {
        if (s == null) {
            return 0;
        }
        int result = 0;
        int previous = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int current = LUT.get(s.charAt(i));
            current = current < previous ? -current : current;
            result += current;
            previous = current;
        }
        return result;
    }
}
