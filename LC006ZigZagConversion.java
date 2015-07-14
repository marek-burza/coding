package leetcode;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 */
public class LC006ZigZagConversion {
    public String convert(String s, int numRows) {
        if (s == null || numRows < 1) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int n = s.length();
        int scan = (numRows - 1) * 2;
        scan = scan == 0 ? 1 : scan;
        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < n; i += scan) {
                buffer.append(s.charAt(i));
                if (0 < row && row < numRows - 1) {
                    int offset = (numRows - 1 - row) * 2;
                    if (i + offset >= n) {
                        break;
                    }
                    buffer.append(s.charAt(i + offset));
                }
            }
        }
        return buffer.toString();
    }

    public static void main(String[] arguments) {
        LC006ZigZagConversion solution = new LC006ZigZagConversion();
        System.out.println(solution.convert("ABCD", 3));
        System.out.println(solution.convert("ABC", 2));
        System.out.println(solution.convert("A", 1));
    }
}
