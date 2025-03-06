package leetcode.lc139_word_break;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 * #medium
 */
public final class LC139WordBreak {
    public boolean wordBreak(final String s, final Set<String> wordDict, final int at, final int length,
            final boolean[] checked) {
        if (checked[at]) {
            return false;
        }
        int limit = Math.min(s.length(), at + length);
        for (int i = at + 1; i <= limit; i++) {
            if (wordDict.contains(s.substring(at, i))) {
                if (i == s.length()) {
                    return true;
                } else {
                    if (wordBreak(s, wordDict, i, length, checked)) {
                        return true;
                    }
                }
            }
        }
        checked[at] = true;
        return false;
    }

    public boolean wordBreak(final String s, final List<String> wordDict) {
        int length = 0;
        for (String word : wordDict) {
            length = Math.max(word.length(), length);
        }
        boolean[] checked = new boolean[s.length()];
        Arrays.fill(checked, false);
        return wordBreak(s, new HashSet<>(wordDict), 0, length, checked);
    }
}
package leetcode.lc139_word_break;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class LC139WordBreakTests {
    public List<String> prepare(final String[] words) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, words);
        return list;
    }

    @Test
    public void testAAndA() throws Exception {
        assertTrue(new LC139WordBreak().wordBreak("a", prepare(new String[] { "a" })));
    }

    @Test
    public void testOther() throws Exception {
        assertFalse(new LC139WordBreak().wordBreak(
                "catsandog", prepare(new String[] { "cats", "dog", "sand", "and", "cat" })));
    }

    @Test
    public void testanother() throws Exception {
        assertTrue(new LC139WordBreak().wordBreak("leetcode", prepare(new String[] { "leet", "code" })));
    }
}
