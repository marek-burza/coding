package leetcode;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LC014LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int i = 0;
        boolean done = false;
        while (!done) {
            for (int j = 0; j < strs.length; j++) {
                if (strs[j] == null) {
                    return "";
                }
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    done = true;
                    break;
                }
            }
            i++;
        }
        return strs[0].substring(0, i - 1);
    }

    public static void main(String[] arguments) {
        System.out.println(new LC014LongestCommonPrefix().longestCommonPrefix(new String[]{"Ala", "Ala ma"}));
    }
}
