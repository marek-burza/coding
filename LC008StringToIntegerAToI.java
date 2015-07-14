package leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */
public class LC008StringToIntegerAToI {
    public int myAtoi(String str) {
        long result = 0;
        long sign = 0;
        boolean white = true, any = false;
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

    public static void main(String[] arguments) {
        LC008StringToIntegerAToI solution = new LC008StringToIntegerAToI();
        System.out.println(solution.myAtoi("-+3241"));
        System.out.println(solution.myAtoi("-3241"));
        System.out.println(solution.myAtoi(" -3241a"));
    }
}
