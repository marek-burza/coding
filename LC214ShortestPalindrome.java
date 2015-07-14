package leetcode;

/**
 * https://leetcode.com/problems/shortest-palindrome/
 */
public class LC214ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String A = s + new StringBuffer(s).reverse().toString();
        int[] cont = new int[A.length()];
        cont[0] = 0;
        for (int i = 1; i < A.length(); i++) {
            int index = cont[i - 1];
            while (index > 0 && A.charAt(index) != A.charAt(i)) {
                index = cont[index - 1];
            }
            cont[i] = index + (A.charAt(index) == A.charAt(i) ? 1 : 0);
        }
        return new StringBuilder(s.substring(cont[cont.length - 1], s.length())).reverse().toString() + s;
    }

    public static void main(String[] arguments) {
        LC214ShortestPalindrome solution = new LC214ShortestPalindrome();
        System.out.println(solution.shortestPalindrome("aacecaaa"));
        System.out.println(solution.shortestPalindrome("abcd"));
    }
}
