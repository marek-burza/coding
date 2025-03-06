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
package leetcode.lc171_excel_sheet_column_number;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC171ExcelSheetColumnNumberTests {
    @Test
    public void testA() throws Exception {
        assertEquals(1, new LC171ExcelSheetColumnNumber().titleToNumber("A"));
    }

    @Test
    public void testB() throws Exception {
        assertEquals(2, new LC171ExcelSheetColumnNumber().titleToNumber("B"));
    }

    @Test
    public void testC() throws Exception {
        assertEquals(3, new LC171ExcelSheetColumnNumber().titleToNumber("C"));
    }

    @Test
    public void testZ() throws Exception {
        assertEquals(26, new LC171ExcelSheetColumnNumber().titleToNumber("Z"));
    }

    @Test
    public void testAA() throws Exception {
        assertEquals(27, new LC171ExcelSheetColumnNumber().titleToNumber("AA"));
    }

    @Test
    public void testAB() throws Exception {
        assertEquals(28, new LC171ExcelSheetColumnNumber().titleToNumber("AB"));
    }

    @Test
    public void testNothing() throws Exception {
        assertEquals(-1, new LC171ExcelSheetColumnNumber().titleToNumber(null));
    }
}
