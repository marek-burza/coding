package leetcode.lc008_string_to_integer_a_to_i;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public final class LC008StringToIntegerAToI {
    public int myAtoi(final String str) {
        long result = 0;
        long sign = 0;
        boolean white = true;
        boolean any = false;
        for (char digit : str.toCharArray()) {
            if (white) {
                if (Character.isWhitespace(digit)) {
                    continue;
                } else {
                    white = false;
                }
            }
            if (Character.isDigit(digit)) {
                if (sign == 0) {
                    sign = 1;
                }
                result *= 10;
                result += digit - '0';
            } else {
                switch (digit) {
                    case '-':
                        if (sign == 0) {
                            sign = -1;
                            continue;
                        } else {
                            return 0;
                        }
                    case '+':
                        if (sign == 0) {
                            sign = 1;
                            continue;
                        } else {
                            return 0;
                        }
                    default:
                        break;
                }
                if (any) {
                    break;
                } else {
                    return 0;
                }
            }
            any = true;
            if (sign * result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (sign * result);
    }
}
