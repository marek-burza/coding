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
```rust
fn convert_to_title(n_value: i32) -> String {
    let mut n = n_value;
    let mut buffer = String::new();
    loop {
        let digit = ('A' as u8 + (n % 26) as u8) as char;
        buffer.push(digit);
        n -= n % 26;
        n /= 26;
        if n == 0 {
            break;
        }
    }
    buffer.chars().rev().collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test1() {
        assert_eq!(String::from("A"), convert_to_title(1));
    }

    #[test]
    fn test2() {
        assert_eq!(String::from("B"), convert_to_title(2));
    }

    #[test]
    fn test3() {
        assert_eq!(String::from("C"), convert_to_title(3));
    }

    #[test]
    fn test26() {
        assert_eq!(String::from("Z"), convert_to_title(26));
    }

    #[test]
    fn test27() {
        assert_eq!(String::from("AA"), convert_to_title(27));
    }

    #[test]
    fn test28() {
        assert_eq!(String::from("AB"), convert_to_title(28));
    }
}
```