package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 */
public class LC139WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict, int at, int length, boolean[] checked) {
        if (checked[at]) return false;
        int limit = Math.min(s.length(), at + length);
        for (int i = at + 1; i <= limit; i++) {
            if (wordDict.contains(s.substring(at, i))) {
                if (i == s.length()) {
                    return true;
                } else {
                    if (wordBreak(s, wordDict, i, length, checked)) return true;
                }
            }
        }
        checked[at] = true;
        return false;
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        int length = 0;
        for (String word : wordDict) {
            length = Math.max(word.length(), length);
        }
        boolean[] checked = new boolean[s.length()];
        Arrays.fill(checked, false);
        return wordBreak(s, wordDict, 0, length, checked);
    }

    public Set<String> prepare(String[] words) {
        Set<String> set = new HashSet();
        for (String word : words) {
            set.add(word);
        }
        return set;
    }

    public static void main(String[] arguments) {
        LC139WordBreak solution = new LC139WordBreak();
        System.out.println(solution.wordBreak("a", solution.prepare(new String[]{"a"})));
    }
}
