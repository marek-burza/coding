package leetcode.lc014_longest_common_prefix;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 */
public final class LC014LongestCommonPrefix {
    public String longestCommonPrefix(final String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int i = 0;
        boolean done = false;
        while (!done) {
            for (String str : strs) {
                if (str == null) {
                    return "";
                }
                if (i >= str.length() || str.charAt(i) != strs[0].charAt(i)) {
                    done = true;
                    break;
                }
            }
            i++;
        }
        return strs[0].substring(0, i - 1);
    }
}
