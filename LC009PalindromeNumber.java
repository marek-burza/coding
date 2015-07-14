package leetcode;

/**
 * https://leetcode.com/problems/palindrome-number/
 */
public class LC009PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        int power = (int) (Math.floor(Math.log10(x)));
        int m = (int) Math.pow(10, power);
        for (int i = 0; i < Math.ceil((power + 1) / 2.0); i++) {
            x -= (x % 10) * m;
            if (x < 0) {
                return false;
            }
            x /= 10;
            m /= 100;
        }
        return x == 0;
    }

    public static void main(String[] arguments) {
        LC009PalindromeNumber solution = new LC009PalindromeNumber();
        System.out.println(solution.isPalindrome(213));
        System.out.println(solution.isPalindrome(456));
        System.out.println(solution.isPalindrome(454));
        System.out.println(solution.isPalindrome(99));
        System.out.println(solution.isPalindrome(1));
        System.out.println(solution.isPalindrome(10));
    }
}
