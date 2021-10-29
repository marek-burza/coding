package leetcode.lc168_excel_sheet_column_title;

/**
 * https://leetcode.com/problems/excel-sheet-column-title/ #easy
 */
public final class LC168ExcelSheetColumnTitle {
    public String convertToTitle(final int nValue) {
        int n = nValue;
        StringBuilder buffer = new StringBuilder();
        do {
            n--;
            char digit = (char) ('A' + (n % 26));
            buffer.append(digit);
            n -= n % 26;
            n /= 26;
        } while (n > 0);
        return buffer.reverse().toString();
    }
}
