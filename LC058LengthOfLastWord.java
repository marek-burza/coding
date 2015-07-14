package leetcode;

/**
 * https://leetcode.com/problems/length-of-last-word/
 */
public class LC058LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        for (; n > 0 && s.charAt(n - 1) == ' '; n--) ;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') return n - i - 1;
        }
        return n;
    }

    public static void main(String[] arguments) {
        LC058LengthOfLastWord solution = new LC058LengthOfLastWord();
        System.out.println(solution.lengthOfLastWord("Hello World"));
    }
}
