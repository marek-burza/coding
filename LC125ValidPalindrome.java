package leetcode;

/**
 * https://leetcode.com/problems/valid-palindrome/
 */
public class LC125ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            char a = s.charAt(i);
            if (!(Character.isAlphabetic(a) || Character.isDigit(a))) {
                i++;
                continue;
            }
            char b = s.charAt(j);
            if (!(Character.isAlphabetic(b) || Character.isDigit(b))) {
                j--;
                continue;
            }
            if (Character.toUpperCase(a) != Character.toUpperCase(b)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public void test() {
        System.out.println("A man, a plan, a canal - Panama: " + isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("race a car: " + isPalindrome("race a car"));
        System.out.println("Ava: " + isPalindrome("Ava"));
        System.out.println("burger: " + isPalindrome("burger"));
    }

    public static void main(String[] arguments) {
        new LC125ValidPalindrome().test();
    }
}
