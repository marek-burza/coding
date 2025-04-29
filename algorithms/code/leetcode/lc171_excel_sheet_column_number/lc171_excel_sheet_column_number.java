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
```rust
use std::string::String;

pub fn title_to_number(s: &str) -> i32 {
    if s.is_null() {
        return -1;
    }

    let mut result = 0;
    for (i, c) in s.chars().enumerate() {
        // Convert character to its corresponding number (A=1, B=2, ..., Z=26)
        let n = ((c as u8 - b'A') + 1) as i32;

        // Multiply the current result by 26 and add the new value
        result = result * 26 + n;
    }

    return result;
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_a() {
        assert_eq!(title_to_number("A"), 1);
    }

    #[test]
    fn test_b() {
        assert_eq!(title_to_number("B"), 2);
    }

    #[test]
    fn test_c() {
        assert_eq!(title_to_number("C"), 3);
    }

    #[test]
    fn test_z() {
        assert_eq!(title_to_number("Z"), 26);
    }

    #[test]
    fn test_aa() {
        assert_eq!(title_to_number("AA"), 27);
    }

    #[test]
    fn test_ab() {
        assert_eq!(title_to_number("AB"), 28);
    }

    #[test]
    fn test_nothing() {
        assert_eq!(title_to_number(""), -1);
    }
}
```