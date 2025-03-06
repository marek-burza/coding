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
package leetcode.lc168_excel_sheet_column_title;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC168ExcelSheetColumnTitleTests {
    @Test
    public void test1() throws Exception {
        assertEquals("A", new LC168ExcelSheetColumnTitle().convertToTitle(1));
    }

    @Test
    public void test2() throws Exception {
        assertEquals("B", new LC168ExcelSheetColumnTitle().convertToTitle(2));
    }

    @Test
    public void test3() throws Exception {
        assertEquals("C", new LC168ExcelSheetColumnTitle().convertToTitle(3));
    }

    @Test
    public void test26() throws Exception {
        assertEquals("Z", new LC168ExcelSheetColumnTitle().convertToTitle(26));
    }

    @Test
    public void test27() throws Exception {
        assertEquals("AA", new LC168ExcelSheetColumnTitle().convertToTitle(27));
    }

    @Test
    public void test28() throws Exception {
        assertEquals("AB", new LC168ExcelSheetColumnTitle().convertToTitle(28));
    }
}
