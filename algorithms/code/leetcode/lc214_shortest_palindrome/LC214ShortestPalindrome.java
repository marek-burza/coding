package leetcode.lc214_shortest_palindrome;

/**
 * https://leetcode.com/problems/shortest-palindrome/ #hard
 */
public final class LC214ShortestPalindrome {
    public String shortestPalindrome(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String a = s + new StringBuffer(s).reverse().toString();
        int[] cont = new int[a.length()];
        cont[0] = 0;
        for (int i = 1; i < a.length(); i++) {
            int index = cont[i - 1];
            while (index > 0 && a.charAt(index) != a.charAt(i)) {
                index = cont[index - 1];
            }
            cont[i] = index + (a.charAt(index) == a.charAt(i) ? 1 : 0);
        }
        return new StringBuilder(s.substring(cont[cont.length - 1], s.length())).reverse().toString() + s;
    }
}
