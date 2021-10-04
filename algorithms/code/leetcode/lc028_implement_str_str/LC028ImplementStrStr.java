package leetcode.lc028_implement_str_str;

/**
 * https://leetcode.com/problems/implement-strstr/
 */
public final class LC028ImplementStrStr {
    public int strStr(final String haystack, final String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
