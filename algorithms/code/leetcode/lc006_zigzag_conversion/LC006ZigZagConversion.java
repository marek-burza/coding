package leetcode.lc006_zigzag_conversion;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 */
public final class LC006ZigZagConversion {
    public String convert(final String s, final int numRows) {
        if (s == null || numRows < 1) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
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
}
