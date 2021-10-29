package leetcode.lc171_excel_sheet_column_number;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/ #easy
 */
public final class LC171ExcelSheetColumnNumber {
    public int titleToNumber(final String s) {
        if (s == null) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result *= 26;
            result += s.charAt(i) - 'A' + 1;
        }
        return result;
    }
}
