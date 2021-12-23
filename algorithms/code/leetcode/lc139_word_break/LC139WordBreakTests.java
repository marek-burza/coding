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
