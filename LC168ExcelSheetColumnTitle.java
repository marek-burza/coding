package leetcode;

/**
 * https://leetcode.com/submissions/detail/27668007/
 */
public class LC168ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuffer buffer = new StringBuffer();
        do {
            n--;
            char digit = (char) ('A' + (n % 26));
            buffer.append(digit);
            n -= n % 26;
            n /= 26;
        } while (n > 0);
        return buffer.reverse().toString();
    }

    public void test() {
        System.out.println("1 -> A: " + convertToTitle(1));
        System.out.println("2 -> B: " + convertToTitle(2));
        System.out.println("3 -> C: " + convertToTitle(3));
        System.out.println("26 -> Z: " + convertToTitle(26));
        System.out.println("27 -> AA: " + convertToTitle(27));
        System.out.println("28 -> AB: " + convertToTitle(28));
    }

    public static void main(String[] arguments) {
        new LC168ExcelSheetColumnTitle().test();
    }
}
