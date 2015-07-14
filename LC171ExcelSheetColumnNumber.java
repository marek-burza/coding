package leetcode;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 */
public class LC171ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
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

    public void test() {
        System.out.println("A -> 1: " + titleToNumber("A"));
        System.out.println("B -> 2: " + titleToNumber("B"));
        System.out.println("C -> 3: " + titleToNumber("C"));
        System.out.println("Z -> 26: " + titleToNumber("Z"));
        System.out.println("AA -> 27: " + titleToNumber("AA"));
        System.out.println("AB -> 28: " + titleToNumber("AB"));
    }

    public static void main(String[] arguments) {
        new LC171ExcelSheetColumnNumber().test();
    }
}
