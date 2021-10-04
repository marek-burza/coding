package leetcode.lc022_generate_parentheses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
public final class LC022GenerateParentheses {
    public void generateParenthesis(final String prefix, final int standing, final int n, final Set<String> found) {
        if (n == 0 && standing == 0) {
            found.add(prefix);
            return;
        }
        // open
        if (n > 0) {
            generateParenthesis(prefix + "(", standing + 1, n - 1, found);
        }
        // close
        if (standing > 0) {
            generateParenthesis(prefix + ")", standing - 1, n, found);
        }
    }

    public List<String> generateParenthesis(final int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        Set<String> found = new TreeSet<>();
        generateParenthesis("(", 1, n - 1, found);
        List<String> result = new ArrayList<>();
        result.addAll(found);
        return result;
    }
}
